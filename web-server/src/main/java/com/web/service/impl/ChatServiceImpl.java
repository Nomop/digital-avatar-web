package com.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.web.dao.ChatMapper;
import com.web.domain.Chat;
import com.web.service.ChatService;
import org.springframework.stereotype.Service;


@Service
public class ChatServiceImpl extends ServiceImpl<ChatMapper, Chat> implements ChatService {
    @Override
    public Long countChats() {
        return this.count();
    }
}
