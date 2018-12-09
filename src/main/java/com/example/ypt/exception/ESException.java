package com.example.ypt.exception;

public class ESException extends RuntimeException {
    private Integer returnCode = 0;

    public ESException() {
        super();
    }

    public ESException(String message) {
        super(message);
        this.returnCode = returnCode;
    }

    public ESException(String message, Throwable cause) {
        super(message, cause);
        this.returnCode = returnCode;
    }

    public ESException(Throwable cause) {
        super(cause);
        this.returnCode = returnCode;
    }

    protected ESException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.returnCode = returnCode;
    }
}
