package com.agileframework.agileclient.common.properties;

import com.agileframework.agileclient.common.annotation.Properties;

/**
 * Created by 佟盟 on 2018/1/11
 */
@Properties(prefix = "agile.kaptcha")
public class KaptchaConfigProperties {
    private static String url;
    private static String border;
    private static String borderColor;
    private static String textproducerFontColor;
    private static String textproducerFontSize;
    private static String imageWidth;
    private static String imageHeight;
    private static String textproducerCharLength;
    private static String textproducerFontNames;

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        KaptchaConfigProperties.url = url;
    }

    public static String getBorder() {
        return border;
    }

    public static void setBorder(String border) {
        KaptchaConfigProperties.border = border;
    }

    public static String getBorderColor() {
        return borderColor;
    }

    public static void setBorderColor(String borderColor) {
        KaptchaConfigProperties.borderColor = borderColor;
    }

    public static String getTextproducerFontColor() {
        return textproducerFontColor;
    }

    public static void setTextproducerFontColor(String textproducerFontColor) {
        KaptchaConfigProperties.textproducerFontColor = textproducerFontColor;
    }

    public static String getTextproducerFontSize() {
        return textproducerFontSize;
    }

    public static void setTextproducerFontSize(String textproducerFontSize) {
        KaptchaConfigProperties.textproducerFontSize = textproducerFontSize;
    }

    public static String getImageWidth() {
        return imageWidth;
    }

    public static void setImageWidth(String imageWidth) {
        KaptchaConfigProperties.imageWidth = imageWidth;
    }

    public static String getImageHeight() {
        return imageHeight;
    }

    public static void setImageHeight(String imageHeight) {
        KaptchaConfigProperties.imageHeight = imageHeight;
    }

    public static String getTextproducerCharLength() {
        return textproducerCharLength;
    }

    public static void setTextproducerCharLength(String textproducerCharLength) {
        KaptchaConfigProperties.textproducerCharLength = textproducerCharLength;
    }

    public static String getTextproducerFontNames() {
        return textproducerFontNames;
    }

    public static void setTextproducerFontNames(String textproducerFontNames) {
        KaptchaConfigProperties.textproducerFontNames = textproducerFontNames;
    }
}
