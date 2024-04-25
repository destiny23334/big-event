package com.destiny.exception;

import com.destiny.pojo.Result;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author zhukang
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception e) {
        e.printStackTrace();
        var errorMessage = e.getMessage();
        return Result.error(StringUtils.hasLength(errorMessage) ? errorMessage : "操作失败");
    }
}
