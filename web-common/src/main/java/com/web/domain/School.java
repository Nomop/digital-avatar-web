package com.web.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("school")
public class School {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("school_uuid")
    private String schoolUuid; // 学校的唯一标识符(UUID)

    @TableField("school_name")
    private String schoolName; // 学校名称

    @TableField("school_address")
    private String schoolAddress; // 学校地址

    @TableField("total_students")
    private Integer totalStudents; // 学校的总学生人数

    @TableField("school_leader")
    private String schoolLeader; // 学校负责人名称

    @TableField("leader_phone")
    private String leaderPhone; // 负责人联系方式

    @TableField("organization")
    private String organization; // 学校所属的组织名称

    @TableField("school_type")
    private String schoolType; // 学校类型（Primary/Junior/Senior）
}
