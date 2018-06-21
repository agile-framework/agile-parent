package com.agileframework.agileclient.common.exception;

/**
 * Created by 佟盟 on 2018/1/3
 * 未找到缓存代理类
 */
public class NotFoundCacheProxyException extends Exception  {
    private static final long serialVersionUID = 1243550305961947415L;

    public NotFoundCacheProxyException() {
    }

    public NotFoundCacheProxyException(String message) {
        super(message);
    }

    public NotFoundCacheProxyException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundCacheProxyException(Throwable cause) {
        super(cause);
    }

    public NotFoundCacheProxyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
