package com.ayang.app.util;

import com.ayang.app.resp.ValidationResult;
import org.apache.commons.collections.CollectionUtils;


import java.util.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;

/**
 * 校验bean的注解属性
 * @author
 *
 */
public class ValidationUtil {

    //初始化一个Validator
    private static Validator validator =  Validation.buildDefaultValidatorFactory().getValidator();

   /* public static String validateModel(Object obj) {
        StringBuffer buffer = new StringBuffer();
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        // 验证某个对象,，其实也可以只验证其中的某一个属性的
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(obj);
        Iterator<ConstraintViolation<Object>> iter = constraintViolations.iterator();
        while (iter.hasNext()) {
            ConstraintViolation<Object> violation = iter.next();
            //String message = violation.getPropertyPath().toString()+":"+violation.getMessage();
            String message = violation.getMessage();
            buffer.append(message+",");
        }
        return buffer.toString();
    }*/


    /**
     * 校验对象
     * @param obj
     * @return
     */
    public static ValidationResult validateModel(Object obj) {
        //校验结果
        ValidationResult result = new ValidationResult();
        Set<ConstraintViolation<Object>> set = validator.validate(obj);
        if(CollectionUtils.isNotEmpty(set)){
            result.setHasErrors(true);
            Map<String,String> errorMsg = new HashMap<>();
            for(ConstraintViolation<Object> cv : set){
                errorMsg.put(cv.getPropertyPath().toString(),cv.getMessage());
            }
            result.setErrorMsg(errorMsg);
        }
        //String message = violation.getPropertyPath().toString()+":"+violation.getMessage();
        return result;
    }


    /**
     * 校验某个属性
     * @param obj
     * @param propertyName
     * @param <T>
     * @return
     */
    public static <T> ValidationResult validateProperty(T obj,String propertyName){
        ValidationResult result = new ValidationResult();
        Set<ConstraintViolation<T>> set = validator.validateProperty(obj,propertyName,Default.class);
        if(CollectionUtils.isNotEmpty(set)){
            result.setHasErrors(true);
            Map<String,String> errorMsg = new HashMap<String,String>();
            for(ConstraintViolation<T> cv : set){
                errorMsg.put(propertyName, cv.getMessage());
            }
            result.setErrorMsg(errorMsg);
        }
        return result;
    }


    public static void main(String[] args) {
        ValidationResult result = new ValidationResult();
        System.out.println(result.isHasErrors());
    }
}
