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

import com.wangbei.entity.Sutra;
import com.wangbei.pojo.Response;
import com.wangbei.service.SutraService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 经书 Controller
 * 
 * @author luomengan
 *
 */
@RestController
@RequestMapping("/sutra")
@Api(description = "经书接口列表")
public class SutraController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public SutraService sutraService;

	@GetMapping("/{id}")
	@ApiOperation(value = "根据id获取经书")
	public Response<Sutra> fetchById(@PathVariable Integer id) {
		return new Response<>(sutraService.getSutraInfo(id));
	}

	@PostMapping("/")
	@ApiOperation(value = "添加经书")
	public Response<Sutra> addition(Sutra sutra) {
		return new Response<>(sutraService.addSutra(sutra));
	}

	@PutMapping("/")
	@ApiOperation(value = "修改经书")
	public Response<Sutra> modification(Sutra sutra) {
		return new Response<>(sutraService.modifySutra(sutra));
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除经书")
	public Response<Integer> delete(@PathVariable Integer id) {
		sutraService.deleteSutra(id);
		return new Response<Integer>(id);
	}

	@GetMapping("/page")
	@ApiOperation(value = "获取经书分页数据")
	public Response<Page<Sutra>> sutras(int page, int limit) {
		return new Response<>((Page<Sutra>) sutraService.sutras(page, limit));
	}
	
	@GetMapping("/list")
	@ApiOperation(value = "获取经书列表")
	public Response<List<Sutra>> list() {
		return new Response<>(sutraService.list());
	}

}
