package com.web.service;

import com.web.domain.Class;
import com.web.dto.ClassPageQueryDto;
import com.web.dto.PageQueryDto;
import com.web.vo.PageResult;

import java.util.List;

public interface ClassService {

    /**
     * 在对应院校下增加一个班级。
     *
     * @param schoolUuid 院校的唯一标识符
     * @param classInfo 班级信息
     * @return 是否添加成功
     */
    boolean addClass(String schoolUuid, Class classInfo);

    /**
     * 批量插入班级
     *
     * @param classList 要插入的班级列表
     * @return 是否插入成功
     */
    boolean addClasses(List<Class> classList);

    /**
     * 删除班级。
     *
     * @param className 班级信息
     * @param schoolUuid 院校的唯一标识符
     * @param grade 年级信息
     * @return 是否删除成功
     */
    boolean deleteClass(String schoolUuid, Integer className,Integer grade);

    /**
     * 修改班级基本信息，包括人数、年级、名称。
     *
     * @param classInfo 班级信息
     * @return 是否修改成功
     */
    boolean updateClass(Class classInfo);

    /**
     * 分页加载某个院校下的班级列表。
     *
     * @param pageQuery 分页请求
     * @return 班级列表
     */
    PageResult getClassesPageQuery(ClassPageQueryDto pageQuery);

    /**
     * 根据院校和班级，查询指定的班级。
     *
     * @param schoolUuid 院校的唯一标识符
     * @param className 班级信息
     * @param grade 年级信息
     * @return 班级信息
     */
    Class getClassBySchoolAndName(String schoolUuid, Integer className,Integer grade);


}
