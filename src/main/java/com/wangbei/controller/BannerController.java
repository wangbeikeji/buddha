package com.wangbei.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wangbei.entity.Banner;
import com.wangbei.pojo.Response;
import com.wangbei.service.BannerService;
import com.wangbei.util.enums.BannerTypeEnum;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * banner图 Controller
 * 
 * @author luomengan
 *
 */
@RestController
@RequestMapping("/banner")
@Api(description = "banner图接口列表")
public class BannerController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public BannerService bannerService;

	@GetMapping("/{id}")
	@ApiOperation(value = "根据id获取banner图")
	public Response<Banner> fetchById(@PathVariable Integer id) {
		return new Response<>(bannerService.getBannerInfo(id));
	}

	@PostMapping("/")
	@ApiOperation(value = "添加banner图")
	public Response<Banner> addition(Banner banner) {
		return new Response<>(bannerService.addBanner(banner));
	}

	@PutMapping("/")
	@ApiOperation(value = "修改banner图")
	public Response<Banner> modification(Banner banner) {
		return new Response<>(bannerService.modifyBanner(banner));
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除banner图")
	public Response<Integer> delete(@PathVariable Integer id) {
		bannerService.deleteBanner(id);
		return new Response<Integer>(id);
	}

	@GetMapping("/page")
	@ApiOperation(value = "获取banner图分页数据")
	public Response<Page<Banner>> banners(int page, int limit) {
		return new Response<>((Page<Banner>) bannerService.banners(page, limit));
	}

	@GetMapping("/pageByType")
	@ApiOperation(value = "根据类型获取banner图分页数据（1:资讯,2:故事,3:入门）")
	public Response<Page<Banner>> bannersByType(int type, int page, int limit) {
		return new Response<>((Page<Banner>) bannerService.bannersByType(BannerTypeEnum.getByIndex(type), page, limit));
	}

	@GetMapping("/list")
	@ApiOperation(value = "获取banner图列表")
	public Response<List<Banner>> list() {
		return new Response<>(bannerService.list());
	}

	@GetMapping("/listByType")
	@ApiOperation(value = "根据类型获取banner图列表（1:资讯,2:故事,3:入门）")
	public Response<List<Banner>> listByType(int type) {
		return new Response<>(bannerService.listByType(BannerTypeEnum.getByIndex(type)));
	}

}
