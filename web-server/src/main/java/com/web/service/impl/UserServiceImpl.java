package com.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.web.constant.ResultCode;
import com.web.dao.BasicUserInfoMapper;
import com.web.dao.UserMapper;
import com.web.domain.BasicUserInfo;
import com.web.domain.GovUser;

import com.web.exception.BusinessException;
import com.web.service.UserService;
import com.web.util.JwtUtil;
import com.web.vo.LoginVo;
import com.web.vo.RegisterVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.Instant;
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


    @Transactional
    @Override
    public String login(LoginVo vo) {
        // 判断用户
        GovUser user = userMapper.selectGovUserByPhone(vo.getPhone());
        Optional.ofNullable(user).orElseThrow(() -> new BusinessException(ResultCode.USER_NOT_FOUND));
        boolean matches = passwordEncoder.matches(vo.getPassword(), user.getPassword());
        if (!matches) {
            throw new BusinessException(ResultCode.USER_CREDENTIALS_ERROR);
        }
        // 组装Authentication
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(vo.getPhone(), vo.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtil.generateToken(user);
        return token;
    }

    @Transactional
    public String register(RegisterVo newUser) {
        // 检查账号是否已存在
        GovUser existingUser = userMapper.selectGovUserByPhone(newUser.getPhone());
        if (existingUser != null) {
            throw new BusinessException(ResultCode.USER_ALREADY_EXISTS);
        }

        //TODO:uid生成
        long timestamp = Instant.now().toEpochMilli(); // 当前时间戳
        int randomNum = new Random().nextInt(10000); // 0-9999 的随机数

        //插入数据
        BasicUserInfo basicUserInfo = new BasicUserInfo();
        basicUserInfo.setUserName(newUser.getPhone());
        basicUserInfo.setUserPhone(newUser.getPhone());
        basicUserInfo.setUserUid(timestamp + String.format("%04d", randomNum));
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
    public GovUser getUserInfo(String phone) {
        return userMapper.selectGovUserByPhone(phone);
    }

}

