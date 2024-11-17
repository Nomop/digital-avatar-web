package com.web.exception;

import com.web.constant.ResultCode;
import lombok.Data;

@Data
public class TokenException extends RuntimeException{

    public TokenException(String message) {
        super(message);
    }

    public TokenException(ResultCode resultCode) {
        super(resultCode.getMessage());
    }
}