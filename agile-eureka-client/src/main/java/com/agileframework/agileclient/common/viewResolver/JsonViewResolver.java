package com.agileframework.agileclient.common.viewResolver;

import com.agileframework.agileclient.common.view.JsonView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.AbstractCachingViewResolver;

import java.util.Locale;

/**
 * Created by 佟盟 on 2017/8/1
 */
public class JsonViewResolver extends AbstractCachingViewResolver {
    private static final String REDIRECT_URL_PREFIX = "redirect:";
    private static final String FORWARD_URL_PREFIX = "forward:";

    @Override
    protected View loadView(String s, Locale locale) {
        if(s.startsWith(FORWARD_URL_PREFIX) || s.startsWith(REDIRECT_URL_PREFIX))return null;
        return new JsonView();
    }
}
