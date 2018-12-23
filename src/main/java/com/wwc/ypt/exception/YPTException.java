package com.wwc.ypt.exception;

public class YPTException extends RuntimeException {

    public YPTException() {
        super();
    }

    public YPTException(String message) {
        super(message);
    }

    public YPTException(String message, Throwable cause) {
        super(message, cause);
    }

    public YPTException(Throwable cause) {
        super(cause);
    }

    public YPTException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
