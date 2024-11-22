package com.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.web.dao.ParentMapper;
import com.web.domain.ParentUser;
import com.web.service.ParentUserService;
import org.springframework.stereotype.Service;

@Service
public class ParentUserServiceImpl extends ServiceImpl<ParentMapper, ParentUser> implements ParentUserService {
    @Override
    public Long countParentUsers() {
        return this.count();
    }
}
