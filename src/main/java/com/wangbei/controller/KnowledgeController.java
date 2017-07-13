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

import com.wangbei.entity.Knowledge;
import com.wangbei.pojo.Response;
import com.wangbei.service.KnowledgeService;
import com.wangbei.util.enums.KnowledgeTypeEnum;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 佛学知识 Controller
 * 
 * @author luomengan
 *
 */
@RestController
@RequestMapping("/knowledge")
@Api(tags = { "佛学知识接口列表" })
public class KnowledgeController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public KnowledgeService knowledgeService;

	@GetMapping("/{id}")
	@ApiOperation(value = "根据id获取佛学知识")
	public Response<Knowledge> fetchById(@PathVariable Integer id) {
		return new Response<>(knowledgeService.getKnowledgeInfo(id));
	}

	@PostMapping("/")
	@ApiOperation(value = "添加佛学知识")
	public Response<Knowledge> addition(Knowledge knowledge) {
		return new Response<>(knowledgeService.addKnowledge(knowledge));
	}

	@PutMapping("/")
	@ApiOperation(value = "修改佛学知识")
	public Response<Knowledge> modification(Knowledge knowledge) {
		return new Response<>(knowledgeService.modifyKnowledge(knowledge));
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除佛学知识")
	public Response<Integer> delete(@PathVariable Integer id) {
		knowledgeService.deleteKnowledge(id);
		return new Response<Integer>(id);
	}

	@GetMapping("/pageByType")
	@ApiOperation(value = "根据类型获取佛学知识分页数据（1:资讯,2:故事,3:入门）")
	public Response<Page<Knowledge>> knowledgesByType(int type, int page, int limit) {
		return new Response<>(
				(Page<Knowledge>) knowledgeService.knowledgesByType(KnowledgeTypeEnum.getByIndex(type), page, limit));
	}

	@GetMapping("/page")
	@ApiOperation(value = "获取佛学知识分页数据")
	public Response<Page<Knowledge>> knowledges(int page, int limit) {
		Response<Page<Knowledge>> result = new Response<>((Page<Knowledge>) knowledgeService.knowledges(page, limit));
		return result;
	}

}
