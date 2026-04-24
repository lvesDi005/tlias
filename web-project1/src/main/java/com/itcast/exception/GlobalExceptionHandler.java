package com.itcast.exception;

import com.itcast.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public Result handleException(Exception e){
        e.printStackTrace();
        log.error("服务器发生异常：{}",e.getMessage());
        return Result.error("服务器发生异常");
    }
}
