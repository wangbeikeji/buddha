package com.wangbei.controller;

import com.wangbei.entity.User;
import com.wangbei.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @author yuyidi 2017-07-06 17:39:59
* @class com.wangbei.controller.UserController
* @description 用户控制器
*/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public User addition(User user) {
        return userService.addUser(user);
    }
}
