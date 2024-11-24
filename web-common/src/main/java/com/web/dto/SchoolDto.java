package com.web.dto;

import lombok.Data;

import java.util.List;

@Data
public class SchoolDto {

    private String schoolName;

    private String schoolAddress;

    private Integer totalStudents;

    private String schoolLeader;

    private String leaderPhone;

    private String organization;//uuid

    private String schoolType;

    private List<List<Integer>> classNum;//[[x,y,n],[...]],x班到y班，有n个人。
    
}
