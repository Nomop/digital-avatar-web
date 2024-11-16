package com.web.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("basic_user_info")
public class BasicUserInfo{

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("user_uid")
    private String userUid;

    @TableField("user_identity")
    private Integer userIdentity;

    @TableField("user_phone")
    private String userPhone;

    @TableField("user_name")
    private String userName;

    @TableField("user_sex")
    private Integer userSex;

    @TableField("user_age")
    private Integer userAge;

    @TableField("user_avatar")
    private String userAvatar;

    @TableField("user_reg_time")
    private LocalDateTime userRegTime;
}
