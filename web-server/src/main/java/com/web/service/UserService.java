package com.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.web.domain.SysUser;
import com.web.vo.LoginVo;
import com.web.vo.RegisterVo;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

public interface UserService extends IService<SysUser> {


    /**
     * 登录
     * @param vo
     * @return
     */
    String login(LoginVo vo);

    /**
     * 注册
     * @param newUser
     * @return
     */
    String register(RegisterVo newUser);

    /**
     * 根据账号获取用户信息
     * @param account
     * @return
     */
    SysUser getUserInfo(String account);
}
