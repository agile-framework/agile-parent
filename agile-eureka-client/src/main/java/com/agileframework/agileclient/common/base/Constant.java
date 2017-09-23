package com.agileframework.agileclient.common.base;

/**
 * Created by 佟盟 on 2017/9/2
 */
public class Constant {
    /**
     * 响应信息相关
     */
    public static class ResponseAbout {
        public final static String HEAD = "head";
        public final static String RESULT = "result";
        public final static String STATE = "state";
        public final static String MSG = "msg";
        public final static String INFO = "info";
        public final static String APP = "app";
        public final static String SERVICE = "service";
        public final static String METHOD = "method";
        public final static String IP = "ip";
        public final static String URL = "url";
    }

    /**
     * 文件相关
     */
    public static class FileAbout {
        public final static String FILE_NAME = "fileName";
        public final static String FILE_SIZE = "fileSize";
        public final static String CONTENT_TYPE = "contentType";
        public final static String UP_LOUD_FILE_INFO = "upLoadFileInfo";
    }

    /**
     * 响应头信息相关
     */
    public static class HeaderAbout {
        public final static String ATTACHMENT = "attachment";
    }

    public static class RegularAbout {
        public final static String NULL = "";
        public final static String QUESTION_MARK = "?";
        public final static String SLASH = "/";
        public final static String BACKSLASH = "\\";
        public final static String AND = "&";
        public final static String EQUAL = "=";
        public final static String AFTER_PARAM = "forward[-_*%#$@+=()^!~`|.,/a-zA-Z0-9]+[&]?";
        public final static String HUMP = "((?=[\\x21-\\x7e]+)[^A-Za-z0-9])";
    }
}
