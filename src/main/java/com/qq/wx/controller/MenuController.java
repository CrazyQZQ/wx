package com.qq.wx.controller;

import com.qq.wx.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MenuController {
    @Autowired
    private MenuService menuService;
    @PutMapping("/menu")
    public String createMenu() {
        return menuService.createMenu();
    }
}
