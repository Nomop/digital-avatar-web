package com.web.service.impl;

import com.alibaba.fastjson.JSON;
import com.web.constant.ResultCode;
import com.web.domain.SysUser;
import com.web.dto.JwtUser;
import com.web.exception.BusinessException;
import com.web.service.UserService;
import com.web.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * Spring Security自动调用，加载用户的身份验证数据
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        // 获取用户信息
        UserService userService = (UserService) applicationContext.getBean("userServiceImpl");
        SysUser user = userService.getUserInfo(account);
        if (user == null) {
            throw new BusinessException(ResultCode.USER_NOT_FOUND);
        }
        // 组装成userDetails
        JwtUser jwtUser = new JwtUser(
                user.getId(),
                user.getAccount(),
                user.getUserName(),
                user.getPassword(),
                Collections.emptyList(),
                user.getEnabled() == 1 ? true : false
        );
        // 存入Redis
        String s = JSON.toJSONString(jwtUser);
        redisUtil.set("user:userDetail:" + user.getAccount(), s);
        return jwtUser;
    }
}
