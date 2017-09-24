package com.agileframework.agileclient.common.exception;

/**
 * Created by 佟盟 on 2017/9/24
 */
public class NoSuchRequestMethodException extends Exception {
    private static final long serialVersionUID = -1794660450328498190L;

    public NoSuchRequestMethodException() {
        super();
    }

    public NoSuchRequestMethodException(String message) {
        super(message);
    }

    public NoSuchRequestMethodException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchRequestMethodException(Throwable cause) {
        super(cause);
    }

    public NoSuchRequestMethodException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
