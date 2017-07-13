package com.wangbei.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wangbei.entity.User;
import com.wangbei.pojo.Response;
import com.wangbei.pojo.UserWithToken;
import com.wangbei.pojo.ValidateCode;
import com.wangbei.security.jwt.TokenAuthenticationService;
import com.wangbei.service.UserService;
import com.wangbei.util.SafeCollectionUtil;

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
    public Response<UserWithToken> addition(User user, Integer validateCode) {
        Response<UserWithToken> response = null;
        if (user.getPhone() != null) {
            ValidateCode validate = SafeCollectionUtil.getValidateCode(user.getPhone());
            if (validate != null && validate.getCode().equals(validateCode)) {
                User userInfo = userService.addUser(user);
                UserWithToken result = new UserWithToken(userInfo);
                result.setToken(TokenAuthenticationService.generateToken(userInfo.getPhone(), ""));
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
