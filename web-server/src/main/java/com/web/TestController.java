package com.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {

    @RequestMapping("/test")
    private String test(){
        System.out.println("一行白鹭上青天");
        log.info("log: 两个黄鹂鸣翠柳");
        return "===========QWQ==========";
    }
}
