package com.ayang.app.controller;

import com.ayang.app.annotation.Validation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LoginController {




    @RequestMapping("/test/login")
    @Validation(error = "404")
    public String login(){

        System.out.println("123");
        return "index";
    }
}
