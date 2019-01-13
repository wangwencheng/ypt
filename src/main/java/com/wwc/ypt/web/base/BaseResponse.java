package com.wwc.ypt.web.base;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseResponse<T> {
    @JsonProperty("returncode")
    private final Integer returnCode;
    private final String message;
    private final T body;

    @JsonCreator
    public BaseResponse(@JsonProperty("returncode") Integer returnCode, @JsonProperty("message") String message, @JsonProperty("body") T body) {
        this.returnCode = returnCode;
        this.message = message;
        this.body = body;
    }
    public static <T> BaseResponse response(Integer returnCode, String message, T body) {
        return new BaseResponse(returnCode, message, body);
    }

    public static BaseResponse success() {
        return success("成功", null);
    }

    public static BaseResponse success(String message) {
        return success(message, null);
    }

    public static <T> BaseResponse success(T body) {
        return success("成功", body);
    }

    public static <T> BaseResponse success(String message, T body) {
        return response(10000, message, body);
    }

    public static BaseResponse error(String message) {
        return error(0, message);
    }

    public static BaseResponse error(Integer returnCode, String message) {
        return response(returnCode, message, null);
    }

    public Integer getReturnCode() {
        return returnCode;
    }

    public String getMessage() {
        return message;
    }

    public T getBody() {
        return body;
    }
}
