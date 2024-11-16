package com.web.controller;

import com.alibaba.fastjson.JSON;
import com.web.dao.UserMapper;
import com.web.dto.WebResult;
import com.web.util.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/test")
@Api("前后端联调测试接口")
public class TestController {

    @GetMapping("/log")
    @ApiOperation("/返回字符")
    private String testLog(){
        System.out.println("一行白鹭上青天");
        log.info("log: 两个黄鹂鸣翠柳");
        return "===========QWQ==========";
    }


    @Autowired
    private RedisUtil redisUtil;


    @GetMapping("/redis")
    public WebResult redisTest() {
        Object o = redisUtil.get("user:info:1");
        Object parse = JSON.parse((String) o);
        return WebResult.success(parse);
    }
}
