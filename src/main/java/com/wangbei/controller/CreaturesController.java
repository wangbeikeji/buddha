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

import com.wangbei.entity.Creatures;
import com.wangbei.pojo.DataResponse;
import com.wangbei.pojo.Response;
import com.wangbei.service.CreaturesService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 生灵 Controller
 * 
 * @author luomengan
 *
 */
@RestController
@RequestMapping("/creatures")
@Api(description = "生灵接口列表")
public class CreaturesController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public CreaturesService creaturesService;

	@GetMapping("/{id}")
	@ApiOperation(value = "根据id获取生灵")
	public Response<Creatures> fetchById(@PathVariable Integer id) {
		return new Response<>(creaturesService.getCreaturesInfo(id));
	}

	@PostMapping("/")
	@ApiOperation(value = "添加生灵")
	public Response<Creatures> addition(Creatures creatures) {
		return new Response<>(creaturesService.addCreatures(creatures));
	}

	@PutMapping("/")
	@ApiOperation(value = "修改生灵")
	public Response<Creatures> modification(Creatures creatures) {
		return new Response<>(creaturesService.modifyCreatures(creatures));
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除生灵")
	public Response<Integer> delete(@PathVariable Integer id) {
		creaturesService.deleteCreatures(id);
		return new Response<Integer>(id);
	}
	
	@PostMapping("/deletes")
	@ApiOperation(value = "批量删除生灵（多个id以逗号分割）")
	public Response<Boolean> deletes(String ids) {
		creaturesService.deleteCreaturess(ids);
		return new Response<Boolean>(true);
	}

	@GetMapping("/page")
	@ApiOperation(value = "获取生灵分页数据")
	public Response<Page<Creatures>> creaturess(int page, int limit) {
		return new Response<>((Page<Creatures>) creaturesService.creaturess(page, limit));
	}
	
	@GetMapping("/list")
	@ApiOperation(value = "获取生灵列表")
	public DataResponse<List<Creatures>> list() {
		return new DataResponse<>(creaturesService.list());
	}

}
