package com.agileframework.agileclient.common.interceptor;

import com.agileframework.agileclient.common.factory.LoggerFactory;
import org.apache.commons.logging.Log;
import org.hibernate.EmptyInterceptor;
import org.springframework.stereotype.Component;

/**
 * Created by 佟盟 on 2017/11/3
 */
@Component
public class JpaInterceptor extends EmptyInterceptor {
    private static final long serialVersionUID = -4455619920711458111L;
    private Log logger = LoggerFactory.createLogger("sql", JpaInterceptor.class);

    @Override
    public String onPrepareStatement(String sql) {
        if(logger.isInfoEnabled()){
            logger.info("\n[SQL语句:]"+sql+"\n");
        }
        return super.onPrepareStatement(sql);
    }
}
