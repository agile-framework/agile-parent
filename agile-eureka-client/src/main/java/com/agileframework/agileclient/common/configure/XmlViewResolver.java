package com.agileframework.agileclient.common.configure;

import com.sun.istack.internal.Nullable;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.xml.MarshallingView;

import java.util.Locale;

/**
 * Created by 佟盟 on 2017/8/1
 */
@Component
public class XmlViewResolver implements ViewResolver {
    @Nullable
    @Override
    public View resolveViewName(String s, Locale locale) throws Exception {
        MarshallingView view = new MarshallingView();
        view.setMarshaller(new XStreamMarshaller());
        return view;    }
}
