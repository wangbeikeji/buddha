package com.wangbei.controller.basedata;

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

import com.wangbei.entity.Divination;
import com.wangbei.pojo.Response;
import com.wangbei.service.DivinationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 灵签 Controller
 * 
 * @author luomengan
 *
 */
@RestController
@RequestMapping("/divination")
@Api(description = "灵签接口列表")
public class DivinationController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public DivinationService divinationService;

	@GetMapping("/{id}")
	@ApiOperation(value = "根据id获取灵签")
	public Response<Divination> fetchById(@PathVariable Integer id) {
		return new Response<>(divinationService.getDivinationInfo(id));
	}

	@PostMapping("/")
	@ApiOperation(value = "添加灵签")
	public Response<Divination> addition(Divination divination) {
		return new Response<>(divinationService.addDivination(divination));
	}

	@PutMapping("/")
	@ApiOperation(value = "修改灵签")
	public Response<Divination> modification(Divination divination) {
		return new Response<>(divinationService.modifyDivination(divination));
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除灵签")
	public Response<Integer> delete(@PathVariable Integer id) {
		divinationService.deleteDivination(id);
		return new Response<Integer>(id);
	}

	@GetMapping("/page")
	@ApiOperation(value = "获取灵签分页数据")
	public Response<Page<Divination>> divinations(int page, int limit) {
		return new Response<>((Page<Divination>) divinationService.divinations(page, limit));
	}

	@GetMapping("/list")
	@ApiOperation(value = "获取灵签列表")
	public Response<List<Divination>> list() {
		return new Response<>(divinationService.list());
	}

}
