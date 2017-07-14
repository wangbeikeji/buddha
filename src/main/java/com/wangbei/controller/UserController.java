package com.wangbei.controller;

import com.wangbei.entity.Hereby;
import com.wangbei.entity.User;
import com.wangbei.pojo.Response;
import com.wangbei.pojo.UserWithToken;
import com.wangbei.pojo.ValidateCode;
import com.wangbei.security.AuthUserDetails;
import com.wangbei.security.jwt.TokenAuthenticationService;
import com.wangbei.service.HerebyService;
import com.wangbei.service.UserService;
import com.wangbei.util.SafeCollectionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * @author yuyidi 2017-07-06 17:39:59
 * @class com.wangbei.controller.UserController
 * @description 用户控制器
 */
@RestController
@RequestMapping("/user")
@Api(description = "用户相关接口列表")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private HerebyService herebyService;

    @PostMapping("/register")
    @ApiOperation(value = "用户注册")
    public Response<UserWithToken> addition(User user, Integer validateCode) {
        Response<UserWithToken> response = null;
        if (user.getPhone() != null) {
            ValidateCode validate = SafeCollectionUtil.getValidateCode(user.getPhone());
            if (validate != null && validate.getCode().equals(validateCode)) {
                User userInfo = userService.addUser(user);
                UserWithToken result = new UserWithToken(userInfo);
                result.setToken(TokenAuthenticationService.generateToken(userInfo.getId(), userInfo.getPhone(), ""));
                response = new Response<>(result);
                return response;
            }
            return new Response<>("3000", "用户验证码发送失败");
        }
        return new Response<>("4001", "手机号不能为空");
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "完善用户信息")
    public Response<User> complete(User user) {
        return new Response<>(userService.modifyUser(user));
    }

    @GetMapping("/getCurrent")
    @ApiOperation(value = "获取当前用户信息")
    public Response<User> getCurrent() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return new Response<>(userService.getUserByPhone(auth.getName()));
    }

    @PutMapping("/resetPassword")
    @ApiOperation(value = "重置密码")
    public Response<UserWithToken> resetPassword(String phone, Integer validateCode, String password) {
        Response<UserWithToken> response = null;
        if (phone != null) {
            ValidateCode validate = SafeCollectionUtil.getValidateCode(phone);
            if (validate != null && validate.getCode().equals(validateCode)) {
                User userInfo = userService.resetPassword(phone, password);
                UserWithToken result = new UserWithToken(userInfo);
                result.setToken(TokenAuthenticationService.generateToken(userInfo.getId(), userInfo.getPhone(), ""));
                response = new Response<>(result);
                return response;
            }
            return new Response<>("3000", "用户验证码发送失败");
        }
        return new Response<>("4001", "手机号不能为空");
    }

    @ApiOperation(value = "请佛")
    @PostMapping("/{id}/hereby/")
    public Response<Hereby> additionHereby(@PathVariable Integer id, @RequestParam(required = true) Integer joss) {
        Response<Hereby> response;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            AuthUserDetails authUserDetails = (AuthUserDetails) authentication.getDetails();
            if (authUserDetails.getUserId() == id) {
                Hereby hereby = herebyService.addHereby(authUserDetails.getUserId(), joss);
                if (hereby != null) {
                    response = new Response<>(hereby);
                    return response;
                }
            }
        }
        return new Response<>("2003", "佛未恭请成功");
    }

    @ApiOperation(value = "恭请其他佛")
    @PutMapping("/{id}/hereby/{hereby}")
    public Response<Hereby> modification(@PathVariable Integer id ,@PathVariable Integer hereby, Integer joss) {
        Response<Hereby> response = new Response<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            AuthUserDetails authUserDetails = (AuthUserDetails) authentication.getDetails();
            if (authUserDetails.getUserId() == id) {
                Hereby result = herebyService.modifyHereby(hereby,authUserDetails.getUserId(), joss);
                if (result != null) {
                    response.setResult(result);
                    return response;
                }
            }
            //若当前用户与请求的用户不相同
            response.setCode("2003");
            response.setMessage("当前用户信息不匹配");
            return response;
        }
        return response;
    }

}
