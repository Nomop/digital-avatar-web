package com.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.web.domain.Class;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

@Mapper
public interface ClassMapper extends BaseMapper<Class> {


    int deleteBySchoolUuid(@Param("schoolUuid") String schoolUuid);
}
