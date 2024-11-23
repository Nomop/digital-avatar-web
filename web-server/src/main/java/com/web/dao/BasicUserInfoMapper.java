package com.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.web.domain.BasicUserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;

@Mapper
public interface BasicUserInfoMapper extends BaseMapper<BasicUserInfo> {


    int insertBasicUserInfo(BasicUserInfo basicUserInfo);

    BasicUserInfo selectByPhone(String phone);
}
