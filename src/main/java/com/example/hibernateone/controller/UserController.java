package com.example.hibernateone.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Author: 张人杰
 * @Date: 2020/5/6
 */
@Controller
@RequestMapping("user")
public class UserController {


    @GetMapping(value = "userGet")
    @ResponseBody
    public String testHello(){
        return "Hello 大兄弟";
    }
    @GetMapping(value = "/login2")
    public String login2(){
        return "/index";
    }
//    @RequestMapping(value = "/login")
//    public String login(){
//        return "/index";
//    }
    @RequestMapping(value = {"/home","/"})
    public String home(){
        return "/login1";
    }
//    @RequestMapping(value = "error")
//    public String error(){
//        return "/error";
//    }
    @RequestMapping(value = {"/user"})
    public String user(){
        return "/user";
    }
    @RequestMapping(value = {"admin"})
    public String admin(){
        return "/admin";
    }
    @RequestMapping("/info")
    @ResponseBody
    public String productInfo(){
        String currentUser = "";
        Object principl = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principl instanceof UserDetails) {
            currentUser = ((UserDetails)principl).getUsername();
        }else {
            currentUser = currentUser+principl.toString();
        }
        return " some product info,currentUser is: "+currentUser;
    }
}
