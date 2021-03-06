package com.kt.cloud.iam.security.exception;

import com.kt.component.exception.UserException;

/**
 * 授权异常类
 * @author jc
 */
public class AuthenticationException extends UserException {

    public AuthenticationException(String errMessage) {
        super(errMessage);
    }

    public AuthenticationException(String errCode, String errMessage) {
        super(errCode, errMessage);
    }

    public AuthenticationException(String errMessage, Throwable e) {
        super(errMessage, e);
    }

    public AuthenticationException(String errorCode, String errMessage, Throwable e) {
        super(errorCode, errMessage, e);
    }

    public static AuthenticationException of(String errMessage) {
        return new AuthenticationException(errMessage);
    }
}
