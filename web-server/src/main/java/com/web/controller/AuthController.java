package com.web.controller;

import com.web.dto.WebResult;
import com.web.service.UserService;
import com.web.util.JwtUtil;
import com.web.dto.LoginDto;
import com.web.dto.RegisterDto;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")
@Api("登录注册登出接口")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public WebResult login(@RequestBody @Validated LoginDto loginDto) {
        // 判断验证码，写死，不要学
        if (!"1234".equals(loginDto.getCode())) {
            return WebResult.error("验证码错误");
        }
        String token = userService.login(loginDto);
        return WebResult.success((Object) token);
    }

    @PostMapping("/register")
    public WebResult register(@RequestBody RegisterDto newUser){
        String token = userService.register(newUser);
        return WebResult.success((Object) token);
    }

    @PostMapping("/logout")
    public WebResult logout(HttpServletRequest request) {
        jwtUtil.logout(request);
        return WebResult.success();
    }

}
