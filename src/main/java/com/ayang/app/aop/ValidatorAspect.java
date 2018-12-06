package com.ayang.app.aop;


import com.ayang.app.annotation.Validation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.bouncycastle.cert.path.validations.BasicConstraintsValidation;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 校验器切面
 */
@Aspect
@Component(value="validator")
public class ValidatorAspect {

    @Pointcut(value="@annotation(com.ayang.app.annotation.Validation)")
    public void cut(){

    }

    @Pointcut(value = "cut()")
    public Object around(ProceedingJoinPoint jp) throws Throwable{
        System.out.println("开始拦截方法："+jp.getSignature().getName());
        //获取第一个参数
        Object[] args = jp.getArgs();
        Map<String,String> errors = new HashMap<>();//保存错误信息
        String errorPage = null;
        if(args.length > 0){
            //获取第一个参数
            Object obj = args[0];
            //获取这个方法上注解
            //获取方法名称
            String methodName = jp.getSignature().getName();
            //获取参数类型
            Class<?>[] parameterTypes =  ((MethodSignature)jp.getSignature()).getParameterTypes();
            //获取方法对象
            Method method = jp.getTarget().getClass().getMethod(methodName, parameterTypes);
            Validation validation = method.getAnnotation(Validation.class);
            //获取value值
            Class<?> group = validation.value();
            errorPage = validation.error(); //获取验证以后转发的页面
            //开始使用JSR303进行验证
            Validator validator = javax.validation.Validation.buildDefaultValidatorFactory().getValidator();
            Set<ConstraintViolation<Object>> set =  validator.validate(obj,group);//只支持一种验证
            for(ConstraintViolation<Object> oo:set){
                errors.put(oo.getPropertyPath().toString(), oo.getMessage());
            }
        }
        if(errors.isEmpty()){//没有错误
            System.out.println("没有错误，，，");
            Object obj = jp.proceed();
            return obj;
        }else{//有错误
            System.out.println("有错误，，，");
            RequestAttributes ra = RequestContextHolder.getRequestAttributes();
            ServletRequestAttributes rsa = (ServletRequestAttributes) ra;
            HttpServletRequest req = rsa.getRequest();
            for(Map.Entry<String, String> en : errors.entrySet()){
                req.setAttribute(en.getKey()+"_error", en.getValue());

            }
            return errorPage;

        }


    }
}
