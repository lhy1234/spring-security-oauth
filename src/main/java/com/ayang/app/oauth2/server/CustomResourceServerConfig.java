package com.ayang.app.oauth2.server;

import com.ayang.app.oauth2.handler.MyOAuth2ResponseExceptionTranslator;
import com.ayang.app.oauth2.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.ayang.app.oauth2.properties.SecurityConstants;
import com.ayang.app.oauth2.properties.SecurityProperties;
import com.ayang.app.oauth2.validate.code.SmsCodeFilter;
import com.ayang.app.oauth2.validate.code.ValidateCodeRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * 资源服务器，和认证服务器在物理上可以在一起也可以分开
 * ClassName: ImoocResourceServerConfig
 * @Description: TODO
 * @author lihaoyang
 * @date 2018年3月13日
 */
@Configuration
@EnableResourceServer
public class CustomResourceServerConfig  extends ResourceServerConfigurerAdapter {

    //自定义的登录成功后的处理器
    @Autowired
    private AuthenticationSuccessHandler customAuthenticationSuccessHandler;

    //自定义的认证失败后的处理器
    @Autowired
    private AuthenticationFailureHandler customAuthenticationFailureHandler;

    //读取用户配置的登录页配置
    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private ValidateCodeRepository validateCodeRepository;


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        //自定义响应异常信息的两种方式：用哪种都可以
        //1、 定义异常转换类生效
        AuthenticationEntryPoint authenticationEntryPoint = new OAuth2AuthenticationEntryPoint();
        ((OAuth2AuthenticationEntryPoint) authenticationEntryPoint).setExceptionTranslator(new MyOAuth2ResponseExceptionTranslator());
        resources.authenticationEntryPoint(authenticationEntryPoint);

        //2，这里把自定义异常加进去
        /*resources.authenticationEntryPoint(new CustomAuthExceptionEntryPoint())
                .accessDeniedHandler(new CustomAccessDeniedHandler());*/

    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        //~~~-------------> 短信验证码过滤器 <------------------
        SmsCodeFilter smsCodeFilter = new SmsCodeFilter();
        smsCodeFilter.setValidateCodeRepository(validateCodeRepository);
        //验证码过滤器中使用自己的错误处理
        smsCodeFilter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);
        //配置的验证码过滤url
        smsCodeFilter.setSecurityProperties(securityProperties);
        smsCodeFilter.afterPropertiesSet();


        http
                //加在登录过滤器前边
                .addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class)

                //----------表单认证相关配置---------------
                .formLogin()
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL) //处理用户认证BrowserSecurityController
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                .successHandler(customAuthenticationSuccessHandler)//自定义的认证后处理器
                .failureHandler(customAuthenticationFailureHandler) //登录失败后的处理
                .and()
                //-----------授权相关的配置 ---------------------
                .authorizeRequests()
                // /authentication/require：处理登录，
                .antMatchers(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                        SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM,//放过登录页不过滤，否则报错
                        SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                        SecurityConstants.SESSION_INVALID_PAGE,
                        SecurityConstants.DEFAULT_USER_REGISTER,
                        SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*").permitAll() //验证码
                .anyRequest()        //任何请求
                .authenticated()    //都需要身份认证

                .and()
                .csrf().disable() //关闭csrf防护
                .apply(smsCodeAuthenticationSecurityConfig);//把短信验证码配置应用上

    }


}