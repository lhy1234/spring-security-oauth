package com.ayang.app.controller;


import com.ayang.app.entity.User;
import com.ayang.app.resp.AppResult;
import com.ayang.app.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 *
 */
@RestController
@RequestMapping("/register")
public class RegisterController {


    @Autowired
    private IUserService userService;



    @RequestMapping("/user")
    public AppResult userRegister(@RequestBody User user){

        userService.userRegister(user);
        user.setBirthday(new Date());
        user.setIsDel(0);
        user.setIsLocked(0);

        return AppResult.ok();
    }




}
