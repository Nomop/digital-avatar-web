package com.web.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.web.dto.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
@TableName("sys_user")
public class SysUser extends BaseEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("account")
    private String account;

    @TableField("user_name")
    private String userName;

    @TableField("password")
    private String password;

    @TableField("enabled")
    private Integer enabled;
    
    /*-----*/

    @TableField("last_login_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastLoginTime;

    @TableField("account_not_expired")
    private Integer accountNotExpired;

    @TableField("account_not_locked")
    private Integer accountNotLocked;

}