package com.agileframework.agileclient.common.exception;

/**
 * Created by 佟盟 on 2017/11/16
 */
public class NoSuchIDException extends Exception {
    public NoSuchIDException() {
        super();
    }

    public NoSuchIDException(String message) {
        super(message);
    }

    public NoSuchIDException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchIDException(Throwable cause) {
        super(cause);
    }

    protected NoSuchIDException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
