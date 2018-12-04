package com.ayang.app.oauth2.handler;


import com.ayang.app.resp.AppResult;
import com.ayang.app.resp.ErrorEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;

/**
 * 自定义异常消息响应，覆盖框架自带的
 *
 */
public class MyOAuth2ResponseExceptionTranslator implements WebResponseExceptionTranslator {

    private static Logger logger = LoggerFactory.getLogger(MyOAuth2ResponseExceptionTranslator.class);


    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
        logger.info("自定义异常："+e);

        Throwable throwable = e.getCause();
        if (throwable instanceof InvalidTokenException) {
            //token失效
            return new ResponseEntity(AppResult.build(ErrorEnum.INVALID_TOKEN.status(),ErrorEnum.INVALID_TOKEN.msg()),  HttpStatus.OK);
        }else{
            //未登录
            return new ResponseEntity(AppResult.build(ErrorEnum.NOT_LOGIN.status(),ErrorEnum.NOT_LOGIN.msg()),  HttpStatus.OK);
        }

        //return new ResponseEntity(AppResult.build(ErrorEnum.METHOD_NOT_ALLOWED.getStatus(),ErrorEnum.METHOD_NOT_ALLOWED.getMsg()), HttpStatus.METHOD_NOT_ALLOWED);
    }
}
