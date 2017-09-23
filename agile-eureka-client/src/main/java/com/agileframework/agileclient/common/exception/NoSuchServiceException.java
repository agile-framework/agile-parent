package com.agileframework.agileclient.common.exception;

/**
 * Created by 佟盟 on 2017/9/23
 */
public class NoSuchServiceException extends Exception {
    private static final long serialVersionUID = 4239743415533491992L;

    public NoSuchServiceException() {
        super();
    }

    public NoSuchServiceException(String message) {
        super(message);
    }

    public NoSuchServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchServiceException(Throwable cause) {
        super(cause);
    }

    public NoSuchServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
