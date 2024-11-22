package com.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.web.domain.GovUser;
import com.web.dto.LoginDto;
import com.web.dto.RegisterDto;

public interface UserService extends IService<GovUser> {


    /**
     * 登录
     * @param loginDto
     * @return
     */
    String login(LoginDto loginDto);

    /**
     * 注册
     * @param newUser
     * @return
     */
    String register(RegisterDto newUser);

    /**
     * 根据账号获取用户信息
     * @param phone
     * @return
     */
    GovUser getUserInfo(String phone);
}
