package com.web.controller;

import com.web.dto.LoginByCodeDto;
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
@Api("身份验证接口")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public WebResult login(@RequestBody @Validated LoginDto loginDto) {
        String token = userService.login(loginDto);
        return WebResult.success((Object) token);
    }

    @PostMapping("/loginByCode")
    public WebResult loginByCode(@RequestBody @Validated LoginByCodeDto loginDto) {
        String token = userService.loginByCode(loginDto);
        return WebResult.success((Object) token);
    }

    @PostMapping("/register")
    public WebResult register(@RequestBody RegisterDto newUser){
        String token = userService.register(newUser);
        return WebResult.success((Object) token);
    }

    @PostMapping("/sendAuthCode")
    public WebResult sendAuthCode(String phone){
        userService.sendAuthCode(phone);
        return WebResult.success();
    }

    @PostMapping("/logout")
    public WebResult logout(HttpServletRequest request) {
        jwtUtil.logout(request);
        return WebResult.success();
    }

}
