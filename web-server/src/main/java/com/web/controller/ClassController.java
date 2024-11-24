package com.web.controller;

import com.web.domain.Class;
import com.web.dto.WebResult;
import com.web.service.ClassService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/home/class")
@Api(tags = "班级管理接口")
public class ClassController {

    @Autowired
    private ClassService classService;

    @ApiOperation("在对应院校下增加一个班级")
    @PostMapping("/add")
    public WebResult<Boolean> addClass(@RequestParam String schoolUuid, @RequestBody Class classInfo) {
        boolean result = classService.addClass(schoolUuid, classInfo);
        if(result){
            return WebResult.success();
        }else{
            return WebResult.error();
        }
    }

    @ApiOperation("删除班级")
    @DeleteMapping("/delete")
    public WebResult<Boolean> deleteClass(@RequestParam String schoolUuid, @RequestParam Integer className,@RequestParam Integer grade) {
        boolean result = classService.deleteClass(schoolUuid, className,grade);
        if(result){
            return WebResult.success();
        }else{
            return WebResult.error();
        }
    }

    @ApiOperation("修改班级基本信息")
    @PutMapping("/update")
    public WebResult<Boolean> updateClass(@RequestBody Class classInfo) {
        boolean result = classService.updateClass(classInfo);
        if(result){
            return WebResult.success();
        }else{
            return WebResult.error();
        }
    }

    @ApiOperation("查询某个院校下的所有班级列表")
    @GetMapping("/list")
    public WebResult<List<Class>> getClassesBySchool(@RequestParam String schoolUuid) {
        List<Class> classList = classService.getClassesBySchool(schoolUuid);
        return WebResult.success(classList);
    }

    @ApiOperation("查询某个院校下的指定班级信息")
    @GetMapping("/get")
    public WebResult<Class> getClassBySchoolAndName(@RequestParam String schoolUuid, @RequestParam Integer className,@RequestParam Integer grade) {
        Class classInfo = classService.getClassBySchoolAndName(schoolUuid, className,grade);
        return WebResult.success(classInfo);
    }
}
