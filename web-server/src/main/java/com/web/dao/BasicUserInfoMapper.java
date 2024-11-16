package com.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.web.domain.BasicUserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BasicUserInfoMapper extends BaseMapper<BasicUserInfo> {


    int insertBasicUserInfo(BasicUserInfo basicUserInfo);
}
