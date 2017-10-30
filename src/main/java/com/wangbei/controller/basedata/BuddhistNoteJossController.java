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

import com.wangbei.entity.BuddhistNoteJoss;
import com.wangbei.pojo.DataResponse;
import com.wangbei.pojo.Response;
import com.wangbei.service.BuddhistNoteJossService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 佛录佛像 Controller
 * 
 * @author luomengan
 *
 */
@RestController
@RequestMapping("/buddhistNoteJoss")
@Api(description = "佛录佛像接口列表")
public class BuddhistNoteJossController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public BuddhistNoteJossService buddhistNoteJossService;

	@GetMapping("/{id}")
	@ApiOperation(value = "根据id获取佛录佛像")
	public Response<BuddhistNoteJoss> fetchById(@PathVariable Integer id) {
		return new Response<>(buddhistNoteJossService.getBuddhistNoteJossInfo(id));
	}

	@PostMapping("/")
	@ApiOperation(value = "添加佛录佛像")
	public Response<BuddhistNoteJoss> addition(BuddhistNoteJoss buddhistNoteJoss) {
		return new Response<>(buddhistNoteJossService.addBuddhistNoteJoss(buddhistNoteJoss));
	}

	@PutMapping("/")
	@ApiOperation(value = "修改佛录佛像")
	public Response<BuddhistNoteJoss> modification(BuddhistNoteJoss buddhistNoteJoss) {
		return new Response<>(buddhistNoteJossService.modifyBuddhistNoteJoss(buddhistNoteJoss));
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除佛录佛像")
	public Response<Integer> delete(@PathVariable Integer id) {
		buddhistNoteJossService.deleteBuddhistNoteJoss(id);
		return new Response<Integer>(id);
	}
	
	@PostMapping("/deletes")
	@ApiOperation(value = "批量删除佛录佛像（多个id以逗号分割）")
	public Response<Boolean> deletes(String ids) {
		buddhistNoteJossService.deleteBuddhistNoteJosss(ids);
		return new Response<Boolean>(true);
	}

	@GetMapping("/page")
	@ApiOperation(value = "获取佛录佛像分页数据")
	public Response<Page<BuddhistNoteJoss>> buddhistNoteJosss(int page, int limit) {
		return new Response<>((Page<BuddhistNoteJoss>) buddhistNoteJossService.buddhistNoteJosss(page, limit));
	}
	
	@GetMapping("/list")
	@ApiOperation(value = "获取佛录佛像列表")
	public DataResponse<List<BuddhistNoteJoss>> list() {
		return new DataResponse<>(buddhistNoteJossService.list());
	}

}
