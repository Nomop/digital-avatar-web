package com.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.web.constant.ResultCode;
import com.web.dao.BasicUserInfoMapper;
import com.web.dao.UserMapper;
import com.web.domain.BasicUserInfo;
import com.web.domain.GovUser;

import com.web.dto.LoginByCodeDto;
import com.web.exception.BusinessException;
import com.web.service.UserService;
import com.web.util.JwtUtil;
import com.web.dto.LoginDto;
import com.web.dto.RegisterDto;
import com.web.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, GovUser> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BasicUserInfoMapper basicUserInfoMapper;

    @Resource
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    @Autowired
    RedisUtil redisUtil;


    @Transactional
    @Override
    public String login(LoginDto loginDto) {
        // 判断用户
        GovUser user = userMapper.selectGovUserByPhone(loginDto.getPhone());
        Optional.ofNullable(user).orElseThrow(() -> new BusinessException(ResultCode.USER_NOT_FOUND));
        boolean matches = passwordEncoder.matches(loginDto.getPassword(), user.getPassword());
        if (!matches) {
            throw new BusinessException(ResultCode.USER_CREDENTIALS_ERROR);
        }
        // 组装Authentication
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDto.getPhone(), loginDto.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtil.generateToken(user);
        return token;
    }

    @Transactional
    @Override
    public String loginByCode(LoginByCodeDto loginByCodeDto){
        String token = "1";
        return token;
    }

    @Transactional
    public String register(RegisterDto newUser) {

        String key = "register:AuthCode:"+newUser.getPhone()+":";

        //检验验证码内容和时间
        Object data = redisUtil.get(key);
        String authcode = data == null ? null : data.toString();
        if(!authcode.equals(newUser.getCode())){
            throw new BusinessException(ResultCode.AUTHCODE_ERR);
        }

        String userUid = UUID.randomUUID().toString();

        //插入数据
        BasicUserInfo basicUserInfo = new BasicUserInfo();
        basicUserInfo.setUserName(newUser.getPhone());
        basicUserInfo.setUserPhone(newUser.getPhone());
        basicUserInfo.setUserUid(userUid);
        basicUserInfo.setUserIdentity(0);
        basicUserInfo.setUserSex(2);
        basicUserInfo.setUserRegTime(LocalDateTime.now());
        basicUserInfoMapper.insertBasicUserInfo(basicUserInfo);

        GovUser newGovUser = new GovUser();
        newGovUser.setUserName(newUser.getPhone());
        newGovUser.setUserPhone(newUser.getPhone());
        newGovUser.setUserId(basicUserInfo.getId());
        newGovUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newGovUser.setEnabled(1);
        userMapper.insertGovUser(newGovUser);

        // 在 Spring Security 中记录用户身份
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(newGovUser.getUserPhone(), null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        String token = jwtUtil.generateToken(newGovUser);
        return token;
    }

    @Override
    public void sendAuthCode(String phone){
        //检查用户是否已注册
        BasicUserInfo user = basicUserInfoMapper.selectByPhone(phone);
        if(user != null) {
            throw new BusinessException(ResultCode.USER_ALREADY_EXISTS);
        }
        
        Random random = new Random();
        String code = String.format("%04d", random.nextInt(10000));


        //TODO:给指定手机号发送验证码

        String key = "register:AuthCode:"+phone+":";
        redisUtil.set(key,code,120);

    }



    @Override
    public GovUser getUserInfo(String phone) {
        return userMapper.selectGovUserByPhone(phone);
    }

}

