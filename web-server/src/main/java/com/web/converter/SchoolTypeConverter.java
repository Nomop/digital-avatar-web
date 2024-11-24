package com.web.converter;

import java.util.HashMap;
import java.util.Map;

public class SchoolTypeConverter {
    // 静态映射表
    private static final Map<String, String> SCHOOL_TYPE_MAP = new HashMap<>();

    static {
        SCHOOL_TYPE_MAP.put("Primary", "小");
        SCHOOL_TYPE_MAP.put("Junior", "初");
        SCHOOL_TYPE_MAP.put("Senior", "高");
    }

    /**
     * 将学校类型转换为对应的中文字符
     *
     * @param schoolType 学校类型（如 "Primary", "Junior", "Senior"）
     * @return 对应的中文字符（如 "小", "初", "高"），如果未匹配则返回 "未知"
     */
    public static String convert(String schoolType) {
        return SCHOOL_TYPE_MAP.getOrDefault(schoolType, "未知");
    }
}
