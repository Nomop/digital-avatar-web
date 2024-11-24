package com.web.controller;

import com.web.domain.School;
import com.web.dto.SchoolDto;
import com.web.dto.WebResult;
import com.web.service.SchoolService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/home/school")
@Api("学校管理接口")
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    @ApiOperation("增加院校及其班级")
    @PostMapping("/add")
    public WebResult<Boolean> addSchoolWithClasses(@RequestBody SchoolDto schoolDto) {
        boolean result = schoolService.addSchoolWithClasses(schoolDto);
        if(result){
            return WebResult.success();
        }else{
            return WebResult.error();
        }
    }

    @ApiOperation("修改院校信息")
    @PutMapping("/update")
    public WebResult<Boolean> updateSchool(@RequestBody School school) {
        boolean result = schoolService.updateSchool(school);
        if(result){
            return WebResult.success();
        }else{
            return WebResult.error();
        }
    }

    @ApiOperation("删除院校及其班级")
    @DeleteMapping("/delete/{schoolUuid}")
    public WebResult<Boolean> deleteSchool(@PathVariable("schoolUuid") String schoolUuid) {
        boolean result = schoolService.deleteSchool(schoolUuid);
        if(result){
            return WebResult.success();
        }else{
            return WebResult.error();
        }
    }

    @ApiOperation("根据院校名称模糊查询")
    @GetMapping("/search")
    public WebResult<List<School>> searchSchoolByName(@RequestParam("schoolName") String schoolName) {
        List<School> schools = schoolService.searchSchoolByName(schoolName);
        return WebResult.success(schools);
    }

    @ApiOperation("获取所有院校信息及班级列表")
    @GetMapping("/list")
    public WebResult<List<School>> getAllSchools() {
        List<School> schools = schoolService.getAllSchools();
        return WebResult.success(schools);
    }
}
