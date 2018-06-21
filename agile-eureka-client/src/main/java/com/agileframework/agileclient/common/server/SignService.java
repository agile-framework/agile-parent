package com.agileframework.agileclient.common.server;

import com.agileframework.agileclient.common.base.RETURN;
import org.springframework.stereotype.Service;

/**
 * 登陆服务
 * Created by mydeathtrial on 2017/3/2
 */
@Service
public class SignService extends MainService {
    public RETURN successSignOut(){
        return RETURN.SUCCESS_SIGN_OUT;
    }
    public RETURN notSignIn(){
        return RETURN.NO_SIGN_IN;
    }
    public RETURN invalidSession(){
        return RETURN.INVALID_SESSION;
    }
    public RETURN expiredSession(){
        return RETURN.EXPIRED_SESSION;
    }
    public RETURN homepage(){
        return RETURN.SUCCESS;
    }
    public RETURN errorSignIn(){
        return RETURN.ERROR_SIGN_IN;
    }
    public RETURN accessDenied(){
        return RETURN.ACCESS_DENIED;
    }
}
