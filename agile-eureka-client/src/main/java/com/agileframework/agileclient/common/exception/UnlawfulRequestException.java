package com.agileframework.agileclient.common.exception;

/**
 * Created by 佟盟 on 2017/9/28
 */
public class UnlawfulRequestException extends Exception {
    public UnlawfulRequestException() {
        super();
    }

    public UnlawfulRequestException(String message) {
        super(message);
    }

    public UnlawfulRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnlawfulRequestException(Throwable cause) {
        super(cause);
    }

    public UnlawfulRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
