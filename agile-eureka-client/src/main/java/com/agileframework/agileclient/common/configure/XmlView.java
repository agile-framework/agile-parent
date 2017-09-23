package com.agileframework.agileclient.common.configure;

import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.xml.MarshallingView;

/**
 * Created by 佟盟 on 2017/8/1
 */
@Component
public class XmlView extends MarshallingView {
    public XmlView() {
        this.setMarshaller(new XStreamMarshaller());
    }
}
