package com.ayang.app.exception;


import org.springframework.security.core.AuthenticationException;

public class UserNotExistException extends AuthenticationException{
    /**
     * @Fields serialVersionUID : TODO
     */
    private static final long serialVersionUID = 1L;

    private String msg ;

    public UserNotExistException(String msg){
        super("user not exist!");
        this.msg = msg;
    }


}
