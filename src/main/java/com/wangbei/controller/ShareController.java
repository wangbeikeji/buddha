package com.wangbei.controller;

import com.wangbei.entity.Divination;
import com.wangbei.service.DivinationService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* @author yuyidi 2017-07-21 16:45:27
* @class com.wangbei.controller.ShareController
* @description 分享
*/
@Api(description = "分享")
@Controller
@RequestMapping("/share")
public class ShareController {

    @Autowired
    private DivinationService divinationService;


    @ApiOperation(value = "求签分享")
    @GetMapping("/divination/{id}")
    public String divination(@PathVariable @ApiParam(value = "求签标识") Integer id,ModelMap modelMap) {
        Divination divination = divinationService.getDivinationInfo(id);
        if (divination != null) {
            modelMap.addAttribute("divination", divination);
        }
        return "share";
    }

}
