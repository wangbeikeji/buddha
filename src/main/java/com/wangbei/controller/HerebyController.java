package com.wangbei.controller;

import com.wangbei.entity.Hereby;
import com.wangbei.entity.Joss;
import com.wangbei.pojo.Response;
import com.wangbei.security.AuthUserDetails;
import com.wangbei.service.HerebyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * @author Created by yuyidi on 2017/7/14.
 * @desc
 */
@RestController
@RequestMapping("/hereby")
public class HerebyController {

    @Autowired
    private HerebyService herebyService;

    @PostMapping("/")
    public Response<Hereby> additionHereby(Integer joss) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            AuthUserDetails authUserDetails = (AuthUserDetails) authentication.getPrincipal();
        }
        return null;
    }

    @PutMapping("/{user}")
    public Response<Hereby> modification(@PathVariable Integer user,Integer joss) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            AuthUserDetails authUserDetails = (AuthUserDetails) authentication.getPrincipal();
        }
        return null;
    }


}
