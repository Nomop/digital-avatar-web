package com.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.web.domain.GovUser;
import com.web.dto.LoginByCodeDto;
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
     * 验证码登录
     * @param loginByCodeDto
     * @return
     */
    String loginByCode(LoginByCodeDto loginByCodeDto);

    /**
     * 注册
     * @param newUser
     * @return
     */
    String register(RegisterDto newUser);

    /**
     * 发送验证码
     * @param phone
     */
    void sendAuthCode(String phone);

    /**
     * 根据账号获取用户信息
     * @param phone
     * @return
     */
    GovUser getUserInfo(String phone);
}
