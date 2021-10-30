package com.diden.user.controller;

import java.util.List;

import com.diden.user.service.UserService;
import com.diden.user.vo.UserVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/user/list")
    public String userList() {
        List<UserVo> userList = userService.userList();
        System.out.println(userList);
        return "/index";
    }
}
