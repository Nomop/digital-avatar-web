package com.web.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.web.dao.ClassMapper;
import com.web.domain.Class;
import com.web.service.ClassService;
import org.springframework.stereotype.Service;

@Service
public class ClassServiceImpl extends ServiceImpl<ClassMapper,Class> implements ClassService {
}
