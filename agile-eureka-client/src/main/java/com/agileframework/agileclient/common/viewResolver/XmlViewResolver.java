package com.agileframework.agileclient.common.viewResolver;

import com.agileframework.agileclient.common.view.XmlView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.AbstractCachingViewResolver;

import java.util.Locale;

/**
 * Created by 佟盟 on 2017/8/1
 */
public class XmlViewResolver extends AbstractCachingViewResolver {

    @Override
    protected View loadView(String s, Locale locale) {
        return new XmlView();
    }
}
