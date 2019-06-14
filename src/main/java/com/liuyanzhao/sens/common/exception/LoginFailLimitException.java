package com.liuyanzhao.sens.common.exception;

import lombok.Data;
import org.springframework.security.authentication.InternalAuthenticationServiceException;

/**
 * @author 言曌
 */
@Data
public class LoginFailLimitException extends InternalAuthenticationServiceException {

    private String msg;

    public LoginFailLimitException(String msg){
        super(msg);
        this.msg = msg;
    }

    public LoginFailLimitException(String msg, Throwable t) {
        super(msg, t);
        this.msg = msg;
    }
}
