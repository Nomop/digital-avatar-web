package com.web.exception;

import com.web.constant.ResultCode;
import lombok.Data;

/**
 * 自定义异常类
 */
@Data
public class BusinessException extends RuntimeException{

    private int code;

    private String message;

    public BusinessException(){}

    public BusinessException(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public BusinessException(int code, String mgs) {
        this.code = code;
        this.message = mgs;
    }

    public BusinessException(String mgs) {
        this.code = 400;
        this.message = mgs;
    }
}
