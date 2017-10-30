package com.wangbei.controller.system;

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

import com.wangbei.entity.SensitiveWord;
import com.wangbei.pojo.DataResponse;
import com.wangbei.pojo.Response;
import com.wangbei.service.SensitiveWordService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 敏感词 Controller
 * 
 * @author luomengan
 *
 */
@RestController
@RequestMapping("/sensitiveWord")
@Api(description = "敏感词接口列表")
public class SensitiveWordController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public SensitiveWordService sensitiveWordService;

	@GetMapping("/{id}")
	@ApiOperation(value = "根据id获取敏感词")
	public Response<SensitiveWord> fetchById(@PathVariable Integer id) {
		return new Response<>(sensitiveWordService.getSensitiveWordInfo(id));
	}

	@PostMapping("/")
	@ApiOperation(value = "添加敏感词")
	public Response<SensitiveWord> addition(SensitiveWord sensitiveWord) {
		return new Response<>(sensitiveWordService.addSensitiveWord(sensitiveWord));
	}

	@PutMapping("/")
	@ApiOperation(value = "修改敏感词")
	public Response<SensitiveWord> modification(SensitiveWord sensitiveWord) {
		return new Response<>(sensitiveWordService.modifySensitiveWord(sensitiveWord));
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除敏感词")
	public Response<Integer> delete(@PathVariable Integer id) {
		sensitiveWordService.deleteSensitiveWord(id);
		return new Response<Integer>(id);
	}
	
	@PostMapping("/deletes")
	@ApiOperation(value = "批量删除敏感词（多个id以逗号分割）")
	public Response<Boolean> deletes(String ids) {
		sensitiveWordService.deleteSensitiveWords(ids);
		return new Response<Boolean>(true);
	}

	@GetMapping("/page")
	@ApiOperation(value = "获取敏感词分页数据")
	public Response<Page<SensitiveWord>> sensitiveWords(int page, int limit) {
		return new Response<>((Page<SensitiveWord>) sensitiveWordService.sensitiveWords(page, limit));
	}
	
	@GetMapping("/list")
	@ApiOperation(value = "获取敏感词列表")
	public DataResponse<List<SensitiveWord>> list() {
		return new DataResponse<>(sensitiveWordService.list());
	}

}
