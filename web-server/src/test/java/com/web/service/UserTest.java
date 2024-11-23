package com.web.service;

import com.DigitalAvatarApplication;
import com.web.dao.UserMapper;
import com.web.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DigitalAvatarApplication.class)
@Slf4j
public class UserTest {

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    UserService userService;

    @Test
    public void authCodeTest() throws InterruptedException {

        String key = "register:AuthCode:"+180777+":";

        redisUtil.set(key,351890574,20);

        Thread.sleep(2000);

        log.info(redisUtil.get(key).toString());
    }


    @Test
    public void sendAuthCode(){
        String phone = "13098939400";
        userService.sendAuthCode(phone);
    }


}
