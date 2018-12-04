package com.ayang.app.oauth2.server;



import com.ayang.app.oauth2.properties.SecurityConstants;
import com.ayang.app.oauth2.support.SimpleResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class AppSecurityController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    //缓存的请求，SpringSecurity通过HttpSessionRequestCache把请求信息缓存到session里
    private RequestCache requestCache = new HttpSessionRequestCache();
    //跳转的工具
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    //@Autowired
    //private SecurityProperties securityProperties;

    /**
     * 当需要身份认证时，跳转到这里处理
     * @Description: TODO
     * @param @param request
     * @param @param response
     * @param @return
     * @return String
     * @throws Exception
     * @throws
     * @author lihaoyang
     * @date 2018年2月28日
     */
    @RequestMapping(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
    @ResponseStatus(code= HttpStatus.UNAUTHORIZED)//返回状态码401 未授权
    public SimpleResponse requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws Exception{
        //拿出缓存的请求 引发跳转的请求
       /* SavedRequest savedRequest = requestCache.getRequest(request, response);
        if(savedRequest != null){
            //拿到引发请求的url
            String targetUrl = savedRequest.getRedirectUrl();
            logger.info("引发跳转的url："+targetUrl);
            if(StringUtils.endsWithIgnoreCase(targetUrl, ".html")){//请求是否以.html结尾
                redirectStrategy.sendRedirect(request, response, securityProperties.getBrowser().getLoginPage());//要跳转的页面，此处应该做成可配置的页面
            }
        }*/
        return new SimpleResponse("未登录");
    }
}
