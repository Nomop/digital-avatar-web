package com.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.web.constant.ResultCode;
import com.web.dao.UserMapper;
import com.web.domain.SysUser;

import com.web.exception.BusinessException;
import com.web.service.UserService;
import com.web.util.JwtUtil;
import com.web.vo.LoginVo;
import com.web.vo.RegisterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, SysUser> implements UserService {

    @Autowired
    private UserMapper userMapper;

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
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getAccount, vo.getAccount());
        SysUser user = userMapper.selectOne(wrapper);
        Optional.ofNullable(user).orElseThrow(() -> new BusinessException(ResultCode.USER_NOT_FOUND));
        boolean matches = passwordEncoder.matches(vo.getPassword(), user.getPassword());
        if (!matches) {
            throw new BusinessException(ResultCode.USER_CREDENTIALS_ERROR);
        }
        // 组装Authentication
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(vo.getAccount(), vo.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtil.generateToken(user);
        return token;
    }

    @Transactional
    public String register(RegisterVo newUser) {
        // 检查账号是否已存在
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getAccount, newUser.getAccount());
        SysUser existingUser = userMapper.selectOne(wrapper);
        if (existingUser != null) {
            throw new BusinessException(ResultCode.USER_ALREADY_EXISTS);
        }
        SysUser newSysUser = new SysUser();
        newSysUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newSysUser.setAccount(newUser.getAccount());
        newSysUser.setUserName(newSysUser.getAccount());
        userMapper.insert(newSysUser);

        // 在 Spring Security 中记录用户身份
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(newSysUser.getAccount(), null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        String token = jwtUtil.generateToken(newSysUser);
        return token;
    }



    @Override
    public SysUser getUserInfo(String account) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getAccount, account);
        SysUser user = userMapper.selectOne(wrapper);
        return user;
    }

}

