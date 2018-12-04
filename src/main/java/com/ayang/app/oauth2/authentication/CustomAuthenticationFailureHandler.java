package com.ayang.app.oauth2.authentication;


import com.ayang.app.oauth2.properties.SecurityProperties;
import com.ayang.app.resp.AppResult;
import com.ayang.app.resp.ErrorEnum;
import org.springframework.stereotype.Component;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;


import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * 登录失败后的处理
 * ClassName: ImoocAuthenticationFailureHandler
 * @Description: 登录失败后的处理
 * @author lihaoyang
 * @date 2018年3月1日
 */
@Component("customAuthenticationFailureHandler")
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler
		/*implements AuthenticationFailureHandler*/ {

    private Logger logger = LoggerFactory.getLogger(getClass());

    //springmvc启动会自动注册一个ObjectMapper
    @Autowired
    private ObjectMapper objectMapper;


    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        logger.info("登录失败");

        //把authentication返回给响应
        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json;charset=UTF-8");
        AppResult appResult = AppResult.build(ErrorEnum.INVALID_USERNAME_OR_PWD.status(),ErrorEnum.INVALID_USERNAME_OR_PWD.msg());
        response.getWriter().write(objectMapper.writeValueAsString(appResult));//值返回异常信息

    }
}
