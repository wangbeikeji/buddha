package com.wangbei.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wangbei.entity.DataVersion;
import com.wangbei.pojo.Response;
import com.wangbei.service.DataVersionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 数据版本 Controller
 * 
 * @author luomengan
 *
 */
@RestController
@RequestMapping("/dataVersion")
@Api(description = "数据版本接口列表")
public class DataVersionController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public DataVersionService dataVersionService;

	@PostMapping("/")
	@ApiOperation(value = "添加数据版本")
	public Response<DataVersion> addition(DataVersion dataVersion) {
		return new Response<>(dataVersionService.addDataVersion(dataVersion));
	}

	@PutMapping("/")
	@ApiOperation(value = "修改数据版本")
	public Response<DataVersion> modification(DataVersion dataVersion) {
		return new Response<>(dataVersionService.modifyDataVersion(dataVersion));
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除数据版本")
	public Response<Integer> delete(@PathVariable Integer id) {
		dataVersionService.deleteDataVersion(id);
		return new Response<Integer>(id);
	}

	@GetMapping("/getCurrent")
	@ApiOperation(value = "获取当前数据版本")
	public Response<String> getCurrent() {
		return new Response<>(dataVersionService.getCurrent());
	}

}
