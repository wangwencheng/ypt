package com.wwc.ypt.advice;

import com.wwc.ypt.base.BaseResponse;
import com.wwc.ypt.exception.YPTException;
import com.google.common.base.Strings;
import com.taobao.api.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class YPTRestControllerAdvice {
    @ExceptionHandler({NullPointerException.class, IllegalArgumentException.class, HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse bindException(Exception e) {
        if (e instanceof NullPointerException) {
            return BaseResponse.error("空指针异常，请排查");
        } else if (e instanceof HttpMessageNotReadableException) {
            log.error("参数解析有问题了", e);
            return BaseResponse.error("没有入参,或者入参格式有问题");
        }else if (e instanceof IllegalArgumentException) {
            log.error("参数非法异常", e);
            return BaseResponse.error("没有入参,或者入参格式有问题");
        }
        return BaseResponse.error(String.format("参数缺失[%s]", e.getMessage()));
    }


    @ExceptionHandler({ApiException.class})
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse bindException(ApiException e) {
        return BaseResponse.error(String.format("调用API异常[%s]", e.getMessage()));
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Throwable.class)
    public BaseResponse exception(Throwable e) {
        return BaseResponse.error("服务器开小差，请稍后再试");
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(YPTException.class)
    public BaseResponse customerException(YPTException e) {
        return BaseResponse.error(Strings.isNullOrEmpty(e.getMessage()) ? "系统自定义异常，请联系管理员" : e.getMessage());
    }
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    public BaseResponse Exception(YPTException e) {
        return BaseResponse.error(Strings.isNullOrEmpty(e.getMessage()) ? "系统自定义异常，请联系管理员" : e.getMessage());
    }
}
