package com.ayang.app.controller;


import com.ayang.app.annotation.Validation;
import com.ayang.app.entity.User;
import com.ayang.app.resp.AppResult;
import com.ayang.app.resp.ErrorEnum;
import com.ayang.app.resp.ValidationResult;
import com.ayang.app.service.IUserService;
import com.ayang.app.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.*;

/**
 *
 */
@Controller
@RequestMapping("/register")
public class RegisterController {


    @Autowired
    private IUserService userService;



    @RequestMapping("/user")
    public AppResult userRegister(@Valid @RequestBody User user,BindingResult errors){
        //数据校验。这样破坏了业务逻辑
        System.out.println("errors: "+errors);
        if(errors.hasErrors()){
            Map<String,String> errMap = new HashMap<>();
            errors.getFieldErrors()
                    .stream()
                    .forEach(err->errMap.put(err.getField(),err.getDefaultMessage()));

            return AppResult.build(ErrorEnum.DATA_VALIDATE_EX.status(),ErrorEnum.DATA_VALIDATE_EX.msg(),errMap);
        }


        userService.userRegister(user);
        user.setBirthday(new Date());
        user.setIsDel(0);
        user.setIsLocked(0);

        return AppResult.ok();
    }



    @RequestMapping("/user2")
    @ResponseBody
    public AppResult userRegister2(@RequestBody User user){
        //数据校验

        ValidationResult valid = ValidationUtil.validateModel(user);
        if(valid.isHasErrors()){
            /*return AppResult.build(ErrorEnum.DATA_VALIDATE_EX.status()
                    ,ErrorEnum.DATA_VALIDATE_EX.msg(),valid.getErrorMsg());*/
            return AppResult.build(ErrorEnum.DATA_VALIDATE_EX,valid.getErrorMsg());
        }


        user.setBirthday(new Date());
        user.setIsDel(0);
        user.setIsLocked(0);
        userService.userRegister(user);
        return AppResult.ok();
    }







}
