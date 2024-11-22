package com.web.service;


import com.DigitalAvatarApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DigitalAvatarApplication.class)
@Slf4j
public class ChatTest {

    @Autowired
    private ChatService chatService;

    @Test
    public void testCountChats() {
        Long chatCount = chatService.countChats();
        log.info("Total Chats: " + chatCount);
    }
}
