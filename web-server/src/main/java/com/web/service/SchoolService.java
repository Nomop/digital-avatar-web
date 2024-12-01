package com.web.service;


import com.web.domain.School;
import com.web.dto.PageQueryDto;
import com.web.dto.SchoolDto;
import com.web.vo.PageResult;

import java.util.List;

public interface SchoolService {
    /**
     * 增加院校及其班级，同时插入多个班级，班级人数相同。
     *
     * @param schoolDto 学校信息和班级数量
     * @return 是否添加成功
     */
    boolean addSchoolWithClasses(SchoolDto schoolDto);

    /**
     * 修改院校信息。
     *
     * @param school 院校信息
     * @return 是否修改成功
     */
    boolean updateSchool(School school);

    /**
     * 删除院校基本信息，并级联删除对应院校下的所有班级。
     *
     * @param schoolUuid 院校的唯一标识符
     * @return 是否删除成功
     */
    boolean deleteSchool(String schoolUuid);

    /**
     * 根据院校名模糊查询，返回该院校信息及班级列表。
     *
     * @param schoolName 院校名称（支持模糊匹配）
     * @return 院校信息及班级列表
     */
    List<School> searchSchoolByName(String schoolName);

    /**
     * 分页加载院校信息
     *
     * @return 所有院校
     */
    PageResult pageQuery(PageQueryDto pageQuery);

}
