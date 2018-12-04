package com.ayang.app.oauth2.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * redis验证码存取策略
 * ClassName: RedisValidateCodeRepository
 * @Description: redis验证码存取策略
 * @author lihaoyang
 * @date 2018年3月14日
 */
public interface ValidateCodeRepository {

    /**
     * 保存验证码
     * @Description: 保存验证码
     * @param @param request ServletWebRequest
     * @param @param code 验证码
     * @param @param validateCodeType 验证码类型
     * @return void
     * @throws
     * @author lihaoyang
     * @date 2018年3月14日
     */
    void save(ServletWebRequest request , ValidateCode code , ValidateCodeType validateCodeType);

    /**
     * 获取验证码
     * @Description: 获取验证码
     * @param @param request
     * @param @param validateCodeType 验证码类型
     * @param @return
     * @return ValidateCode
     * @throws
     * @author lihaoyang
     * @date 2018年3月14日
     */
    ValidateCode get(ServletWebRequest request , ValidateCodeType validateCodeType);

    /**
     * 移出验证码
     * @Description: 移出验证码
     * @param @param request
     * @param @param validateCodeType 验证码类型
     * @return void
     * @throws
     * @author lihaoyang
     * @date 2018年3月14日
     */
    void remove(ServletWebRequest request , ValidateCodeType validateCodeType);
}
