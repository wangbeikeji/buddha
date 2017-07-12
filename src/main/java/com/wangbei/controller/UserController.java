package com.wangbei.controller;

import com.wangbei.entity.User;
import com.wangbei.pojo.Response;
import com.wangbei.pojo.ValidateCode;
import com.wangbei.service.UserService;
import com.wangbei.util.SafeCollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @PostMapping("/register")
    public Response<User> addition(User user, Integer validateCode) {
        User result = null;
        Response<User> response = null;
        if (user.getPhone() != null) {
            ValidateCode validate = SafeCollectionUtil.getValidateCode(user.getPhone());
            if (validate != null && validate.getCode().equals(validateCode)) {
                result = userService.addUser(user);
                response = new Response<>(result);
                return response;
            }
            return new Response<>("3000", "用户验证码发送失败");
        }
        return new Response<>("4001", "手机号不能为空");
    }

    @PutMapping("/{id}")
    public Response<User> complete(User user) {
        return new Response<>(userService.modifyUser(user));
    }

//    @PostMapping(value = "/login")
//    public Response<User> login(String phone, String password) {
//        return new Response<>(userService.getUserByPhoneAndPassword(phone, password));
//    }

    @PostMapping(value = "/login")
    public User login(String phone, String password) {
        return userService.getUserByPhoneAndPassword(phone, password);
    }
}
