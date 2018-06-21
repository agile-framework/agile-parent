package com.agileframework.agileclient.common.properties;

/**
 * Created by 佟盟 on 2018/2/1
 */
public class FileConfigProperty {
    private static long maxUploadSize = 204800;
    private static String defaultEncoding = "utf-8";
    private static String includeFormat = "txt,excel";

    public long getMaxUploadSize() {
        return maxUploadSize;
    }

    public void setMaxUploadSize(long maxUploadSize) {
        this.maxUploadSize = maxUploadSize;
    }

    public String getDefaultEncoding() {
        return defaultEncoding;
    }

    public void setDefaultEncoding(String defaultEncoding) {
        this.defaultEncoding = defaultEncoding;
    }

    public String getIncludeFormat() {
        return includeFormat;
    }

    public void setIncludeFormat(String includeFormat) {
        this.includeFormat = includeFormat;
    }
}
