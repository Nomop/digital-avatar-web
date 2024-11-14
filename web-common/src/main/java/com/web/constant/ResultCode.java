package com.web.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 返回状态码
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ResultCode {

    SUCCESS(200, "成功"),

    BAD_REQUEST(400, "请求错误"),

    UNAUTHORIZED(401, "用户未认证，请登录"),

    FORBIDDEN(403, "禁止访问"),

    NOT_FOUND(404, "内容未找到"),

    METHOD_NOT_ALLOW(405, "不支持的方法"),

    SERVER_ERROR(500, "服务器内部发生错误"),

    /* 参数错误：1000～1999 */
    PARAM_NOT_VALID(1001, "参数无效"),
    PARAM_IS_BLANK(1002, "参数为空"),
    PARAM_TYPE_ERROR(1003, "参数类型错误"),
    PARAM_NOT_COMPLETE(1004, "参数缺失"),

    /* 用户错误 */
    USER_NOT_LOGIN(2001, "用户未登录"),
    USER_ACCOUNT_EXPIRED(2002, "账号已过期"),
    USER_CREDENTIALS_ERROR(2003, "密码错误"),
    USER_CREDENTIALS_EXPIRED(2004, "密码过期"),
    USER_ACCOUNT_DISABLE(2005, "账号不可用"),
    USER_ACCOUNT_LOCKED(2006, "账号被锁定"),
    USER_ACCOUNT_NOT_EXIST(2007, "账号不存在"),
    USER_ACCOUNT_ALREADY_EXIST(2008, "账号已存在"),
    USER_ACCOUNT_USE_BY_OTHERS(2009, "账号下线"),
    USER_NOT_FOUND(2010, "用户不存在"),

    TOKEN_INVALID(3001, "token无效"),
    TOKEN_TIMEOUT(3002, "token已过期"),

    CAPTCHA_ERR(4001, "验证码错误");

    /**
     * 返回码
     */
    private Integer code;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 根据code获取message
     * @param code
     * @return
     */
    public static String getMessageByCode(Integer code) {
        for (ResultCode ele : values()) {
            if (ele.getCode().equals(code)) {
                return ele.getMessage();
            }
        }
        return null;
    }
}
