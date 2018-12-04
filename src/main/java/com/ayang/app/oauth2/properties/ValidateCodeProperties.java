package com.ayang.app.oauth2.properties;

/**
 * 验证码配置
 * ClassName: ValidateCodeProperties
 * @Description: 验证码配置,验证码有图片验证码、短信验证码等，所以再包一层
 * @author lihaoyang
 * @date 2018年3月2日
 */
public class ValidateCodeProperties {



    //短信验证码配置
    private SmsCodeProperties sms = new SmsCodeProperties();

    public SmsCodeProperties getSms() {
        return sms;
    }

    public void setSms(SmsCodeProperties sms) {
        this.sms = sms;
    }





}