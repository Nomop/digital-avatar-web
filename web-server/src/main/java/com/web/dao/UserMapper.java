package com.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.web.domain.GovUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<GovUser> {

    GovUser selectGovUserById(Integer id);

    GovUser selectGovUserByPhone(String phone);

    int insertGovUser(GovUser govUser);
}
