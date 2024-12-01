package com.web.controller;

import com.web.vo.WebResult;
import com.web.service.ChatService;
import com.web.service.ParentUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home/data")
@Api("数据看板接口")
public class DataBoardController {

    @Autowired
    ChatService chatService;

    @Autowired
    ParentUserService parentUserService;

    @GetMapping("/getRegisterCount")
    public WebResult getRegisteredUserCount(){
        Long userCount = parentUserService.countParentUsers();
        return WebResult.success(userCount);
    }

    @GetMapping("/getChatCount")
    public WebResult getChatCount(){
        Long ChatCount = chatService.countChats();
        return WebResult.success(ChatCount);
    }


}
