package com.ayang.app.exception;



public class UserNotExistException extends RuntimeException{
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
