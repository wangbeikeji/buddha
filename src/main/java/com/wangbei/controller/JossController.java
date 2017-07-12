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

import com.wangbei.entity.Joss;
import com.wangbei.pojo.Response;
import com.wangbei.service.JossService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 佛信息 Controller
 * 
 * @author luomengan
 *
 */
@RestController
@RequestMapping("/joss")
@Api(tags = { "佛信息接口列表" })
public class JossController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public JossService jossService;

	@GetMapping("/{id}")
	@ApiOperation(value = "根据id获取佛信息")
	public Response<Joss> fetchById(@PathVariable Integer id) {
		return new Response<>(jossService.getJossInfo(id));
	}

	@PostMapping("/")
	@ApiOperation(value = "添加佛信息")
	public Response<Joss> addition(Joss joss) {
		return new Response<>(jossService.addJoss(joss));
	}

	@PutMapping("/")
	@ApiOperation(value = "修改佛信息")
	public Response<Joss> modification(Joss joss) {
		return new Response<>(jossService.modifyJoss(joss));
	}

	@DeleteMapping("/")
	@ApiOperation(value = "删除佛信息")
	public Response<Integer> delete(@PathVariable Integer id) {
		jossService.deleteJoss(id);
		return new Response<Integer>(id);
	}

	@GetMapping("/page")
	@ApiOperation(value = "获取佛信息分页数据")
	public Response<Page<Joss>> josss(int page, int limit) {
		return new Response<>((Page<Joss>) jossService.josss(page, limit));
	}

}
