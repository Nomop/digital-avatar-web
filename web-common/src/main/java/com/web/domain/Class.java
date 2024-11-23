package com.web.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("class")
public class Class {


    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("class_name")
    private String className;

    @TableField("class_size")
    private Integer classSize;

    @TableField("grade")
    private Integer grade;

    @TableField("school_uuid")
    private String schoolUuid;
}
