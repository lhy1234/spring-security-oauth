package com.ayang.app.resp;

public enum ErrorEnum {

    FAILURE(0, "失败" ),
    SUCCESS(1, "成功"),


    DATA_VALIDATE_EX(2,"数据校验异常"),




    // http响应相关

    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    UNAUTHORIZED(401,"Unauthorized"),

    //100x ~认证授权相关
    NOT_LOGIN(1001,"未登录"),
    INVALID_TOKEN(1002,"token无效"),
    INVALID_USERNAME_OR_PWD(1003,"用户名或密码错误"),
    ;


    private final int status;
    private final String msg;

    ErrorEnum(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public int status() {
        return status;
    }



    public String msg() {
        return msg;
    }


}
