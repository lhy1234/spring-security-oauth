package com.ayang.app.oauth2.validate.code;

import java.util.concurrent.TimeUnit;

import com.ayang.app.util.JsonUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;



/**
 * redis验证码存取策略
 * ClassName: RedisValidateCodeRepository
 * @Description: redis验证码存取策略
 * @author lihaoyang
 * @date 2018年3月14日
 */
@Component
public class RedisValidateCodeRepository implements ValidateCodeRepository{

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;



    @Override
    public void save(ServletWebRequest request, ValidateCode code, ValidateCodeType validateCodeType) {
        String key = buildKey(request, validateCodeType);
        logger.info("【验证码redis存储实现】redis存进去了一个新的key：{}，值:{}",key, JsonUtils.objectToJson(code));
        redisTemplate.opsForValue().set(key, code, 30, TimeUnit.MINUTES);
    }

    @Override
    public ValidateCode get(ServletWebRequest request, ValidateCodeType validateCodeType) {
        Object value = redisTemplate.opsForValue().get(buildKey(request, validateCodeType));
        if(value == null){
            return null;
        }
        return (ValidateCode) value;
    }

    @Override
    public void remove(ServletWebRequest request, ValidateCodeType validateCodeType) {
        String key = buildKey(request, validateCodeType);
        logger.info("【验证码redis存储实现】redis删除了一个key：{}",key);
        redisTemplate.delete(key);
    }

    /**
     * 构建验证码在redis中的key
     * @Description: 构建验证码在redis中的key
     * @param @return
     * @return String  验证码在redis中的key
     * @throws
     * @author lihaoyang
     * @date 2018年3月14日
     */
    private String buildKey(ServletWebRequest request , ValidateCodeType validateCodeType){
        //获取设备id
        String deviceId = request.getHeader("deviceId");
        if(StringUtils.isBlank(deviceId)){
            throw new ValidateCodeException("【验证码redis存储实现】deviceId为空，请求头中未携带deviceId参数");
        }
        return "code:" + validateCodeType.toString().toLowerCase()+":"+deviceId;
    }


}