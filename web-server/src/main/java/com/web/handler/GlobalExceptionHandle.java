package com.web.handler;

import com.web.constant.ResultCode;
import com.web.dto.WebResult;
import com.web.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandle {

    /**
     * 拦截业务异常
     * @param e
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    public WebResult handlerBusinessException(Exception e, HttpServletRequest request, HttpServletResponse response) {
        log.error("业务异常信息：{}", e.getMessage());
        e.printStackTrace();
        // 不同异常返回不同状态码
        WebResult result = WebResult.error(((BusinessException) e).getCode(), e.getMessage());
        return result;
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public WebResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request, HttpServletResponse response) {
        log.error("请求参数异常：{}", e.getMessage());
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        // 不同异常返回不同状态码
        WebResult result = WebResult.error(ResultCode.BAD_REQUEST.getCode(), allErrors.get(0).getDefaultMessage());
        return result;
    }

    /**
     * 处理其他异常
     * @param e
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public WebResult handleException(Exception e, HttpServletRequest request, HttpServletResponse response){
        log.error("根异常信息：{}", e.getMessage());
        e.printStackTrace();
        // 不同异常返回不同状态码
        WebResult result = WebResult.error(ResultCode.SERVER_ERROR.getCode(), ResultCode.SERVER_ERROR.getMessage());
        return result;
    }
}
