package com.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.web.dao.ClassMapper;
import com.web.domain.Class;
import com.web.dto.ClassPageQueryDto;
import com.web.service.ClassService;
import com.web.vo.PageResult;
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
    public PageResult getClassesPageQuery(ClassPageQueryDto pageQuery){
        Page<Class> page = new Page<>(pageQuery.getPage(), pageQuery.getPageSize(), true);
        LambdaQueryWrapper<Class> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Class::getSchoolUuid, pageQuery.getSchoolUuid());
        Page<Class> classPage = this.page(page, queryWrapper);
        long total = classPage.getTotal();
        PageResult pageResult = new PageResult(total, classPage.getRecords());
        return pageResult;
        
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
