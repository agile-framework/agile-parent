package com.agileframework.agileclient.common.properties;


import com.agileframework.agileclient.common.annotation.Properties;

/**
 * Created by 佟盟 on 2018/2/1
 */
@Properties(prefix = "agile.security")
public class SecurityProperties {
    private static String loginUrl = "/signService/signIn";
    private static String loginOutUrl = "/signService/signOut";
    private static String loginOutSuccessUrl = "/signService/successSignOut";
    private static String notLoginUrl = "/signService/notSignIn";
    private static String invalidSessionUrl = "/signService/invalidSession";
    private static String expiredSessionUrl = "/signService/expiredSession";
    private static String successUrl = "/signService/homepage";
    private static String failureUrl = "/signService/errorSignIn";
    private static String accessDenied = "/signService/accessDenied";
    private static String csrfAllowUrl;
    private static String csrfAllowMethods = "GET,HEAD,TRACE,OPTIONS";
    private static String verificationCode = "verification";

    public static String getLoginUrl() {
        return loginUrl;
    }

    public static void setLoginUrl(String loginUrl) {
        SecurityProperties.loginUrl = loginUrl;
    }

    public static String getLoginOutUrl() {
        return loginOutUrl;
    }

    public static void setLoginOutUrl(String loginOutUrl) {
        SecurityProperties.loginOutUrl = loginOutUrl;
    }

    public static String getLoginOutSuccessUrl() {
        return loginOutSuccessUrl;
    }

    public static void setLoginOutSuccessUrl(String loginOutSuccessUrl) {
        SecurityProperties.loginOutSuccessUrl = loginOutSuccessUrl;
    }

    public static String getNotLoginUrl() {
        return notLoginUrl;
    }

    public static void setNotLoginUrl(String notLoginUrl) {
        SecurityProperties.notLoginUrl = notLoginUrl;
    }

    public static String getInvalidSessionUrl() {
        return invalidSessionUrl;
    }

    public static void setInvalidSessionUrl(String invalidSessionUrl) {
        SecurityProperties.invalidSessionUrl = invalidSessionUrl;
    }

    public static String getExpiredSessionUrl() {
        return expiredSessionUrl;
    }

    public static void setExpiredSessionUrl(String expiredSessionUrl) {
        SecurityProperties.expiredSessionUrl = expiredSessionUrl;
    }

    public static String getSuccessUrl() {
        return successUrl;
    }

    public static void setSuccessUrl(String successUrl) {
        SecurityProperties.successUrl = successUrl;
    }

    public static String getFailureUrl() {
        return failureUrl;
    }

    public static void setFailureUrl(String failureUrl) {
        SecurityProperties.failureUrl = failureUrl;
    }

    public static String getAccessDenied() {
        return accessDenied;
    }

    public static void setAccessDenied(String accessDenied) {
        SecurityProperties.accessDenied = accessDenied;
    }

    public static String getCsrfAllowUrl() {
        return csrfAllowUrl;
    }

    public static void setCsrfAllowUrl(String csrfAllowUrl) {
        SecurityProperties.csrfAllowUrl = csrfAllowUrl;
    }

    public static String getCsrfAllowMethods() {
        return csrfAllowMethods;
    }

    public static void setCsrfAllowMethods(String csrfAllowMethods) {
        SecurityProperties.csrfAllowMethods = csrfAllowMethods;
    }

    public static String getVerificationCode() {
        return verificationCode;
    }

    public static void setVerificationCode(String verificationCode) {
        SecurityProperties.verificationCode = verificationCode;
    }
}
