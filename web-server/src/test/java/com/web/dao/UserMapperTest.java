package com.web.dao;

import com.DigitalAvatarApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = DigitalAvatarApplication.class)
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void select() {
/*        List<User> list = userMapper.selectList(null);
        Assert.assertEquals(6, list.size());
        list.forEach(System.out::println);*/
    }
}