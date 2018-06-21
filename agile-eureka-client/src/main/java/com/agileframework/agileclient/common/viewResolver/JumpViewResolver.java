package com.agileframework.agileclient.common.viewResolver;

import com.agileframework.agileclient.common.view.ForwardView;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.AbstractCachingViewResolver;
import org.springframework.web.servlet.view.AbstractUrlBasedView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Locale;

/**
 * Created by 佟盟 on 2018/3/26
 */
public class JumpViewResolver extends AbstractCachingViewResolver implements Ordered {
    private static final String REDIRECT_URL_PREFIX = "redirect:";
    private static final String FORWARD_URL_PREFIX = "forward:";
    private int order = 2147483647;
    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    protected View loadView(String viewName, Locale locale) {
        String forwardUrl;
        if (viewName.startsWith(REDIRECT_URL_PREFIX)) {
            forwardUrl = viewName.substring(REDIRECT_URL_PREFIX.length());
            RedirectView view = new RedirectView(forwardUrl, true, true);
            return this.applyLifecycleMethods(viewName, view);
        } else if (viewName.startsWith(FORWARD_URL_PREFIX)) {
            forwardUrl = viewName.substring(FORWARD_URL_PREFIX.length());
            return new ForwardView(forwardUrl);
        } else {
            return null;
        }
    }

    private View applyLifecycleMethods(String viewName, AbstractUrlBasedView view) {
        ApplicationContext context = this.getApplicationContext();
        Object initialized = context.getAutowireCapableBeanFactory().initializeBean(view, viewName);
        if (initialized instanceof View) {
            return (View)initialized;
        }

        return view;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
