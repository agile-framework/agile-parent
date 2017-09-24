package com.agileframework.agileclient.common.exception;

/**
 * Created by 佟盟 on 2017/9/23
 */
public class NoSuchRequestServiceException extends Exception {
    private static final long serialVersionUID = 4239743415533491992L;

    public NoSuchRequestServiceException() {
        super();
    }

    public NoSuchRequestServiceException(String message) {
        super(message);
    }

    public NoSuchRequestServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchRequestServiceException(Throwable cause) {
        super(cause);
    }

    public NoSuchRequestServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
