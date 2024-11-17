package com.web.dto;

import com.web.constant.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一返回体
 * @param <T>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WebResult<T> {

    private Boolean success;

    private Integer code;

    private String message;

    private T data;

    public static WebResult success() {
        WebResult result = new WebResult();
        result.success = true;
        result.code = 200;
        result.message = "成功";
        return result;
    }

    public static WebResult success(String message) {
        WebResult result = new WebResult();
        result.success = true;
        result.code = 200;
        result.message = message;
        return result;
    }

    public static WebResult success(Object data) {
        WebResult result = new WebResult();
        result.success = true;
        result.code = 200;
        result.message = "成功";
        result.data = data;
        return result;
    }

    public static WebResult error() {
        WebResult result = new WebResult();
        result.success = false;
        result.code = 400;
        result.message = "失败";
        return result;
    }

    public static WebResult error(String message) {
        WebResult result = new WebResult();
        result.success = false;
        result.code = 400;
        result.message = message;
        return result;
    }

    public static WebResult error(int code, String message) {
        WebResult result = new WebResult();
        result.success = false;
        result.code = code;
        result.message = message;
        return result;
    }

    public static WebResult error(ResultCode resultCode) {
        WebResult result = new WebResult();
        result.success = false;
        result.code = resultCode.getCode();
        result.message = resultCode.getMessage();
        return result;
    }
}

