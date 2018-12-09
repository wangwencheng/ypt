package com.example.ypt.advice;

import com.example.ypt.base.BaseResponse;
import com.example.ypt.exception.ESException;
import com.taobao.api.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ESRestControllerAdvice {
    @ExceptionHandler({ESException.class, NullPointerException.class, ESException.class, IllegalArgumentException.class, HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse bindException(Exception e) {
        if (e instanceof ESException) {
            return BaseResponse.error("ES服务器异常，请排查");
        } else if (e instanceof NullPointerException) {
            return BaseResponse.error("空指针异常，请排查");
        } else if (e instanceof HttpMessageNotReadableException) {
            log.error("参数解析有问题了", e);
            return BaseResponse.error("没有入参,或者入参格式有问题");
        }
        return BaseResponse.error(String.format("参数缺失[%s]", e.getMessage()));
    }


    @ExceptionHandler({ApiException.class})
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse bindException(ApiException e) {
        return BaseResponse.error(String.format("调用API异常[%s]", e.getMessage()));
    }
}
