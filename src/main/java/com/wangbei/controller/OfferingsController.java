package com.wangbei.controller;

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

import com.wangbei.entity.Offerings;
import com.wangbei.pojo.Response;
import com.wangbei.service.OfferingsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 供品 Controller
 * 
 * @author luomengan
 *
 */
@RestController
@RequestMapping("/offerings")
@Api(tags = { "供品接口列表" })
public class OfferingsController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public OfferingsService offeringsService;

	@GetMapping("/{id}")
	@ApiOperation(value = "根据id获取供品")
	public Response<Offerings> fetchById(@PathVariable Integer id) {
		return new Response<>(offeringsService.getOfferingsInfo(id));
	}

	@PostMapping("/")
	@ApiOperation(value = "添加供品")
	public Response<Offerings> addition(Offerings offerings) {
		return new Response<>(offeringsService.addOfferings(offerings));
	}

	@PutMapping("/")
	@ApiOperation(value = "修改供品")
	public Response<Offerings> modification(Offerings offerings) {
		return new Response<>(offeringsService.modifyOfferings(offerings));
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除供品")
	public Response<Integer> delete(@PathVariable Integer id) {
		offeringsService.deleteOfferings(id);
		return new Response<Integer>(id);
	}

	@GetMapping("/page")
	@ApiOperation(value = "获取供品分页数据")
	public Response<Page<Offerings>> offeringss(int page, int limit) {
		return new Response<>((Page<Offerings>) offeringsService.offeringss(page, limit));
	}

}
