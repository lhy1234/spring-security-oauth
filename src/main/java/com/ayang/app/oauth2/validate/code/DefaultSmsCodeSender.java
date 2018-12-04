package com.ayang.app.oauth2.validate.code;

/**
 * 默认的短信验证码发送类
 * ClassName: DefaultSmsCodeSender
 * @Description: TODO
 * @author lihaoyang
 * @date 2018年3月7日
 */
public class DefaultSmsCodeSender implements SmsCodeSender{

    @Override
    public void send(String mobile, String code) {
        //存数据库
        System.err.println("向手机 :"+mobile+" 发送短信验证码 :"+code);
    }

}