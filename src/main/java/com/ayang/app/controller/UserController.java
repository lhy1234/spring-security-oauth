package com.ayang.app.controller;


import com.ayang.app.entity.User;
import com.ayang.app.resp.AppResult;
import com.ayang.app.service.IUserService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lihaoyang123
 * @since 2018-11-15
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/getById")
    public @ResponseBody
    AppResult getById(Integer id){
        User user = userService.selectById(id);
        return AppResult.ok(user);
    }

    @RequestMapping("/addUser")
    public @ResponseBody boolean addUser(User user){
        return userService.insert(user);
    }

    @RequestMapping("/getByUsername")
    public @ResponseBody User getByUsername(String username){
        return userService.selectOne(new EntityWrapper<User>().eq("username",username));
    }


    /**
     *{
     "access_token": "903d10bf-c853-48e1-8f28-5585bb064a47",
     "token_type": "bearer",
     "refresh_token": "16e03b55-09d8-4435-b76b-aed989f3c18a",
     "expires_in": 43199,
     "scope": "all"
     }
     }
     *
     *
     *
     *
     */
    @GetMapping("/me")
    public @ResponseBody Object getCurrentUser(@AuthenticationPrincipal UserDetails user) {
        return user;
    }

}

