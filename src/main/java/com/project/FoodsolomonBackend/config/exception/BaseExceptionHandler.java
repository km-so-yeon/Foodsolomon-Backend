package com.project.FoodsolomonBackend.config.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(value = { BaseException.class })
    public BaseResponse<Object> handleRequestException(BaseException ex) {

        return new BaseResponse<>(ex.getStatus());

    }
}
