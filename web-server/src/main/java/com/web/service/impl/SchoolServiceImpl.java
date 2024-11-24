package com.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.web.dao.ClassMapper;
import com.web.dao.SchoolMapper;
import com.web.domain.Class;
import com.web.domain.School;
import com.web.dto.SchoolDto;
import com.web.service.ClassService;
import com.web.service.SchoolService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class SchoolServiceImpl extends ServiceImpl<SchoolMapper, School> implements SchoolService {

    @Autowired
    private SchoolMapper schoolMapper;

    @Autowired
    private ClassMapper classMapper;

    @Autowired
    private ClassService classService;

    @Override
    @Transactional
    public boolean addSchoolWithClasses(SchoolDto schoolDto) {
        School school = new School();
        BeanUtils.copyProperties(schoolDto,school);
        String uid = UUID.randomUUID().toString();
        school.setSchoolUuid(uid);

        int schoolInserted = schoolMapper.insert(school);
        if (schoolInserted == 0) {
            return false;
        }

        List<List<Integer>> allGrade = schoolDto.getClassNum();

        List<Class> list = new ArrayList<>();
        for(int grade = 1; grade<=allGrade.size();grade++){
            List<Integer> curGrade = allGrade.get(grade-1);
            int curClass = curGrade.get(0);
            int lastClass = curGrade.get(1);
            for(;curClass<=lastClass;curClass++){
                Class tempClass = new Class();
                tempClass.setClassName(curClass);
                tempClass.setClassSize(curGrade.get(2));
                tempClass.setSchoolUuid(uid);
                tempClass.setGrade(grade);
                list.add(tempClass);
            }

        }

        if(!classService.addClasses(list)){
            return false;
        }
        return true;
    }

    @Override
    public boolean updateSchool(School school) {
        return schoolMapper.updateById(school) > 0;
    }

    @Override
    @Transactional
    public boolean deleteSchool(String schoolUuid) {
        classMapper.deleteBySchoolUuid(schoolUuid);
        return schoolMapper.deleteBySchoolUuid(schoolUuid) > 0;
    }

    @Override
    public List<School> searchSchoolByName(String schoolName) {
        LambdaQueryWrapper<School> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(School::getSchoolName, schoolName);
        return this.list(queryWrapper);
    }

    @Override
    public List<School> getAllSchools() {
        return this.list();
    }
}
