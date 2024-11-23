package com.web.dao;

import com.DigitalAvatarApplication;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.web.domain.Class;
import com.web.domain.School;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DigitalAvatarApplication.class)
public class SchoolTest {

    @Autowired
    SchoolMapper schoolMapper;

    @Autowired
    ClassMapper classMapper;

    @Test
    public void select(){
        QueryWrapper<School> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("school_name","1");
        List<School> schools = schoolMapper.selectList(queryWrapper);
        System.out.println(schools);

    }

    @Test
    public void classTest(){
        QueryWrapper<Class> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("class_name","1");
        List<Class> classes = classMapper.selectList(queryWrapper);
        System.out.println(classes);
    }
}
