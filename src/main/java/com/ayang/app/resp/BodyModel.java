package com.ayang.app.resp;



import java.io.Serializable;

public class BodyModel<T> implements Serializable {

    private static final long serialVersionUID = 4658408931472585946L;
    private T result;

    private String msg;

    private Integer code;


    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public BodyModel() {
        this.msg = ErrorEnum.SUCCESS.msg();
        this.code = ErrorEnum.SUCCESS.status();
    }
}
