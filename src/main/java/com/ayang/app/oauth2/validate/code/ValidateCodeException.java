package com.ayang.app.oauth2.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * ClassName: ValidateCodeException
 * @Description: 验证码错误异常，继承spring security的认证异常
 * @author lihaoyang
 * @date 2018年3月2日
 */
public class ValidateCodeException extends AuthenticationException {

    /**
     * @Fields serialVersionUID : TODO
     */
    private static final long serialVersionUID = 1L;

    public ValidateCodeException(String msg) {
        super(msg);
    }

}