package com.web.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
public class LoginByCodeDto {

    @NotBlank(message = "账号不能为空")
    private String phone;

    @NotBlank(message = "验证码不能为空")
    private String authCode;
}
