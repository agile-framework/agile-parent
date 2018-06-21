package com.agileframework.agileclient.common.exception;

/**
 * Created by 佟盟 on 2018/1/3
 */
public class NonSupportDBException extends Exception {
    private static final long serialVersionUID = -7054352135497189048L;

    public NonSupportDBException() {
        super("不支持的数据库类型");
    }

    public NonSupportDBException(String message) {
        super(message);
    }

    public NonSupportDBException(String message, Throwable cause) {
        super(message, cause);
    }

    public NonSupportDBException(Throwable cause) {
        super(cause);
    }

    public NonSupportDBException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
