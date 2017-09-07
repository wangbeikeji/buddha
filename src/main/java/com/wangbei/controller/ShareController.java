package com.wangbei.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wangbei.entity.BuddhistMusic;
import com.wangbei.entity.Divination;
import com.wangbei.pojo.BuddhistMusicCategoryInfo;
import com.wangbei.service.BuddhistMusicCategoryService;
import com.wangbei.service.BuddhistMusicService;
import com.wangbei.service.DivinationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

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
	
	@Autowired
	private BuddhistMusicService buddhistMusicService;
	
	@Autowired
	private BuddhistMusicCategoryService buddhistMusicCategoryService;

	@ApiOperation(value = "求签分享")
	@GetMapping("/divination/{id}")
	public String divination(@PathVariable @ApiParam(value = "求签标识") Integer id, ModelMap modelMap) {
		Divination divination = divinationService.getDivinationInfo(id);
		if (divination != null) {
			modelMap.addAttribute("divination", divination);
		}
		return "share";
	}

	@ApiOperation(value = "佛音分享")
	@GetMapping("/music/{id}")
	public String music(@PathVariable @ApiParam(value = "佛音id") Integer id, ModelMap modelMap) {
		BuddhistMusic music = buddhistMusicService.getBuddhistMusicInfo(id);
		if (music != null) {
			modelMap.addAttribute("music", music);
		}
		return "musicShare/music";
	}
	
	@ApiOperation(value = "佛音专辑分享")
	@GetMapping("/musicAlbum/{id}")
	public String musicAlbum(@PathVariable @ApiParam(value = "佛音专辑id") Integer id, ModelMap modelMap) {
		BuddhistMusicCategoryInfo category = buddhistMusicCategoryService.getBuddhistMusicCategoryInfo(id);
		if(category != null) {
			modelMap.addAttribute("category", category);
			List<BuddhistMusic> musicList = buddhistMusicService.listByCategory(id);
			modelMap.addAttribute("musicList", musicList);
		}
		return "musicShare/musicAlbum";
	}

}
