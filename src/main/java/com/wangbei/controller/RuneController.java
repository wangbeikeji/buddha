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

import com.wangbei.entity.Rune;
import com.wangbei.pojo.Response;
import com.wangbei.service.RuneService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 符文 Controller
 * 
 * @author luomengan
 *
 */
@RestController
@RequestMapping("/rune")
@Api(tags = { "符文接口列表" })
public class RuneController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public RuneService runeService;

	@GetMapping("/{id}")
	@ApiOperation(value = "根据id获取符文")
	public Response<Rune> fetchById(@PathVariable Integer id) {
		return new Response<>(runeService.getRuneInfo(id));
	}

	@PostMapping("/")
	@ApiOperation(value = "添加符文")
	public Response<Rune> addition(Rune rune) {
		return new Response<>(runeService.addRune(rune));
	}

	@PutMapping("/")
	@ApiOperation(value = "修改符文")
	public Response<Rune> modification(Rune rune) {
		return new Response<>(runeService.modifyRune(rune));
	}

	@DeleteMapping("/")
	@ApiOperation(value = "删除符文")
	public Response<Integer> delete(@PathVariable Integer id) {
		runeService.deleteRune(id);
		return new Response<Integer>(id);
	}

	@GetMapping("/page")
	@ApiOperation(value = "获取符文分页数据")
	public Response<Page<Rune>> runes(int page, int limit) {
		return new Response<>((Page<Rune>) runeService.runes(page, limit));
	}

}
