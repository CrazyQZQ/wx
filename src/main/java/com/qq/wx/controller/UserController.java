package com.qq.wx.controller;

import com.qq.wx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public void getUsers(){
        userService.getUsers();
    }
//    @GetMapping("/user")
//    public void getUserInfo(){
//        userService.getUserInfo();
//    }
}
