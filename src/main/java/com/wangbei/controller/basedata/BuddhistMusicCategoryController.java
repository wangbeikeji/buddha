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

import com.wangbei.pojo.BuddhistMusicCategoryInfo;
import com.wangbei.pojo.DataResponse;
import com.wangbei.pojo.Response;
import com.wangbei.service.BuddhistMusicCategoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 佛音类别 Controller
 * 
 * @author luomengan
 *
 */
@RestController
@RequestMapping("/buddhistMusicCategory")
@Api(description = "佛音类别接口列表")
public class BuddhistMusicCategoryController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public BuddhistMusicCategoryService buddhistMusicCategoryService;

	@GetMapping("/{id}")
	@ApiOperation(value = "根据id获取佛音类别")
	public Response<BuddhistMusicCategoryInfo> fetchById(@PathVariable Integer id) {
		return new Response<>(buddhistMusicCategoryService.getBuddhistMusicCategoryInfo(id));
	}

	@PostMapping("/")
	@ApiOperation(value = "添加佛音类别")
	public Response<BuddhistMusicCategoryInfo> addition(BuddhistMusicCategoryInfo buddhistMusicCategory) {
		return new Response<>(buddhistMusicCategoryService.addBuddhistMusicCategory(buddhistMusicCategory));
	}

	@PutMapping("/")
	@ApiOperation(value = "修改佛音类别")
	public Response<BuddhistMusicCategoryInfo> modification(BuddhistMusicCategoryInfo buddhistMusicCategory) {
		return new Response<>(buddhistMusicCategoryService.modifyBuddhistMusicCategory(buddhistMusicCategory));
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除佛音类别")
	public Response<Integer> delete(@PathVariable Integer id) {
		buddhistMusicCategoryService.deleteBuddhistMusicCategory(id);
		return new Response<Integer>(id);
	}
	
	@PostMapping("/deletes")
	@ApiOperation(value = "删除佛音类别")
	public Response<Boolean> deletes(String ids) {
		String[] idsArr = ids.split(",");
		for(String id : idsArr) {
			buddhistMusicCategoryService.deleteBuddhistMusicCategory(Integer.parseInt(id));
		}
		return new Response<Boolean>(true);
	}

	@GetMapping("/listTopCategory")
	@ApiOperation(value = "获取佛音类别列表")
	public Response<List<BuddhistMusicCategoryInfo>> listTopCategory() {
		return new Response<>(buddhistMusicCategoryService.listTopCategory());
	}
	
	@GetMapping("/listRecommendCategory")
	@ApiOperation(value = "获取佛音类别推荐列表")
	public Response<List<BuddhistMusicCategoryInfo>> listRecommendCategory() {
		return new Response<>(buddhistMusicCategoryService.listRecommendCategory());
	}
	
	@GetMapping("/listAlbumCategoryByParent")
	@ApiOperation(value = "根据父级id获取专辑类别列表", notes = "1佛经 28密咒 54梵音 88清音")
	public Response<Page<BuddhistMusicCategoryInfo>> listAlbumCategoryByParent(int parentId, int page, int limit) {
		return new Response<>(buddhistMusicCategoryService.listAlbumCategoryByParent(parentId, page, limit));
	}
	
	/*******************************************以下方法为后台管理专用**************************************************/
	
	@GetMapping("/adminList")
	@ApiOperation(value = "获取佛音类别列表（后台管理使用）")
	public DataResponse<List<BuddhistMusicCategoryInfo>> adminList() {
		return new DataResponse<>(buddhistMusicCategoryService.list());
	}

}
