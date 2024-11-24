package com.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.web.domain.School;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;


@Mapper
public interface SchoolMapper extends BaseMapper<School> {


    int deleteBySchoolUuid(@Param("schoolUuid") String schoolUuid);

}
