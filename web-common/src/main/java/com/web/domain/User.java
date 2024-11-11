package com.web.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user")
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}