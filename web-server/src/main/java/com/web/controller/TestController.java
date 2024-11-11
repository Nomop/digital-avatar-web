package com.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
    private String test(){
        System.out.println("一行白鹭上青天");
        log.info("log: 两个黄鹂鸣翠柳");
        return "===========QWQ==========";
    }
}
