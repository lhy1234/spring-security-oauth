package com.ayang.app.oauth2.validate.code;

import com.ayang.app.resp.AppResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 验证码Control
 * ClassName: ValidateCodeController
 * @Description: TODO
 * @author lihaoyang
 * @date 2018年3月1日
 */
@RestController
public class ValidateCodeController {

    @Autowired
    private ValidateCodeGenerator smsCodeGenerator;//短信验证码

    @Autowired
    private SmsCodeSender smsCodeSender;//短信验证码发送接口

    /**
     * session存取验证码策略
     * TODO:重构为调用存储策略存取验证码
     * 此处注入接口，浏览器和app有不同的实现
     */
    @Autowired
    private ValidateCodeRepository validateCodeRepository;



    /**
     * 短信验证码
     * @Description: TODO
     * @param @param request
     * @param @param response
     * @param @throws IOException
     * @return void
     * @throws Exception
     * @throws
     * @author lihaoyang
     * @date 2018年3月7日
     */
    @GetMapping("/verifycode/sms")
    public AppResult createSmsCode(HttpServletRequest request, HttpServletResponse response) throws Exception{

        //调验证码生成接口方式
        ValidateCode smsCode = smsCodeGenerator.generator(new ServletWebRequest(request));
        validateCodeRepository.save(new ServletWebRequest(request) , smsCode, ValidateCodeType.SMS);
        //获取手机号
        String mobile = ServletRequestUtils.getRequiredStringParameter(request, "mobile");
        //发送短信验证码
        smsCodeSender.send(mobile, smsCode.getCode());
        return AppResult.ok();
    }


}