package com.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.web.domain.GovUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GovUserMapper extends BaseMapper<GovUser> {

    GovUser selectGovUser(Integer id);
}
