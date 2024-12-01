package com.web.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("school_info")
public class School {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("school_uuid")
    private String schoolUuid;

    @TableField("school_name")
    private String schoolName;

    @TableField("school_address")
    private String schoolAddress;

    @TableField("total_students")
    private Integer totalStudents;

    @TableField("school_leader")
    private String schoolLeader;

    @TableField("leader_phone")
    private String leaderPhone;

    @TableField("organization")
    private String organization;

    @TableField("school_type")
    private String schoolType; // 学校类型（Primary/Junior/Senior）
}
