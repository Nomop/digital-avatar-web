package com.web.controller;

import com.web.dto.WebResult;
import com.web.service.UserService;
import com.web.util.JwtUtil;
import com.web.vo.LoginVo;
import com.web.vo.RegisterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public WebResult login(@RequestBody @Validated LoginVo vo) {
        // 判断验证码，写死，不要学
        if (!"1234".equals(vo.getCode())) {
            return WebResult.error("验证码错误");
        }
        String token = userService.login(vo);
        return WebResult.success((Object) token);
    }

    @PostMapping("/register")
    public WebResult register(@RequestBody RegisterVo newUser){
        String token = userService.register(newUser);
        return WebResult.success((Object) token);
    }

    @PostMapping("/logout")
    public WebResult logout(HttpServletRequest request) {
        jwtUtil.logout(request);
        return WebResult.success();
    }

}
