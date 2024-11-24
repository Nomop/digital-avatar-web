package com.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.web.dao.ClassMapper;
import com.web.domain.Class;
import com.web.service.ClassService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassServiceImpl extends ServiceImpl<ClassMapper, Class> implements ClassService {

    @Override
    public boolean addClass(String schoolUuid, Class classInfo) {
        classInfo.setSchoolUuid(schoolUuid);
        return this.save(classInfo);
    }

    @Override
    public boolean addClasses(List<Class> classList) {
        return this.saveBatch(classList);
    }

    @Override
    public boolean deleteClass(String schoolUuid, Integer className,Integer grade) {
        LambdaQueryWrapper<Class> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Class::getSchoolUuid, schoolUuid)
                .eq(Class::getClassName, className)
                .eq(Class::getGrade, grade);
        return this.remove(queryWrapper);
    }

    @Override
    public boolean updateClass(Class classInfo) {
        return this.updateById(classInfo);
    }

    @Override
    public List<Class> getClassesBySchool(String schoolUuid) {
        LambdaQueryWrapper<Class> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Class::getSchoolUuid, schoolUuid);
        return this.list(queryWrapper);
    }

    @Override
    public Class getClassBySchoolAndName(String schoolUuid, Integer className,Integer grade) {
        LambdaQueryWrapper<Class> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Class::getSchoolUuid, schoolUuid)
                .eq(Class::getClassName, className)
                .eq(Class::getGrade, grade);
        return this.getOne(queryWrapper);
    }


}
