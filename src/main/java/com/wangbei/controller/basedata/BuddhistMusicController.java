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

import com.wangbei.entity.BuddhistMusic;
import com.wangbei.pojo.AppHomePageData;
import com.wangbei.pojo.DataResponse;
import com.wangbei.pojo.Response;
import com.wangbei.service.BuddhistMusicService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 佛音 Controller
 * 
 * @author luomengan
 *
 */
@RestController
@RequestMapping("/buddhistMusic")
@Api(description = "佛音接口列表")
public class BuddhistMusicController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public BuddhistMusicService buddhistMusicService;

	@GetMapping("/{id}")
	@ApiOperation(value = "根据id获取佛音")
	public Response<BuddhistMusic> fetchById(@PathVariable Integer id) {
		return new Response<>(buddhistMusicService.getBuddhistMusicInfo(id));
	}

	@PostMapping("/")
	@ApiOperation(value = "添加佛音")
	public Response<BuddhistMusic> addition(BuddhistMusic buddhistMusic) {
		return new Response<>(buddhistMusicService.addBuddhistMusic(buddhistMusic));
	}

	@PutMapping("/")
	@ApiOperation(value = "修改佛音")
	public Response<BuddhistMusic> modification(BuddhistMusic buddhistMusic) {
		return new Response<>(buddhistMusicService.modifyBuddhistMusic(buddhistMusic));
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除佛音")
	public Response<Integer> delete(@PathVariable Integer id) {
		buddhistMusicService.deleteBuddhistMusic(id);
		return new Response<Integer>(id);
	}
	
	@PostMapping("/deletes")
	@ApiOperation(value = "删除佛音")
	public Response<Boolean> deletes(String ids) {
		String[] idsArr = ids.split(",");
		for(String id : idsArr) {
			buddhistMusicService.deleteBuddhistMusic(Integer.parseInt(id));
		}
		return new Response<Boolean>(true);
	}

	@GetMapping("/page")
	@ApiOperation(value = "获取佛音分页数据")
	public Response<Page<BuddhistMusic>> buddhistMusics(int page, int limit) {
		return new Response<>((Page<BuddhistMusic>) buddhistMusicService.buddhistMusics(page, limit));
	}

	@GetMapping("/list")
	@ApiOperation(value = "获取佛音列表")
	public Response<List<BuddhistMusic>> list() {
		return new Response<>(buddhistMusicService.list());
	}

	@GetMapping("/listByCategory")
	@ApiOperation(value = "根据类别获取佛音列表")
	public Response<List<BuddhistMusic>> listByCategory(int categoryId) {
		return new Response<>(buddhistMusicService.listByCategory(categoryId));
	}

	@GetMapping("/pageByCategoryId")
	@ApiOperation(value = "根据类别获取佛音列表")
	public Response<Page<BuddhistMusic>> pageByCategoryId(int categoryId, int page, int limit) {
		return new Response<>(buddhistMusicService.pageByCategoryId(categoryId, page, limit));
	}

	@GetMapping("/getAppHomePageData")
	@ApiOperation(value = "获取App佛音首页数据", notes = "topRecommendList中type为1表示专辑，为2表示音乐")
	public Response<AppHomePageData> getAppHomePageData(int categoryId) {
		return new Response<>(buddhistMusicService.getAppHomePageData(categoryId));
	}

	@PostMapping("/play")
	@ApiOperation(value = "播放（用于增加播放次数）")
	public Response<String> play(int id) {
		buddhistMusicService.paly(id);
		return new Response<>("播放成功");
	}

	/******************************************* 以下方法为后台管理专用 **************************************************/

	@GetMapping("/autoCreateByDir")
	@ApiOperation(value = "根据目录自动生成佛音记录")
	public Response<String> autoCreate(String dir) throws Exception {
		buddhistMusicService.autoCreateByDir2(dir);
		return new Response<>("生成成功");
	}
	
	@GetMapping("/buildMusicDuration")
	@ApiOperation(value = "程序获取音乐时长")
	public Response<String> buildMusicDuration(boolean isBuildAll) {
		buddhistMusicService.buildMusicDuration(isBuildAll);
		return new Response<>("获取成功");
	}
	
	@GetMapping("/adminList")
	@ApiOperation(value = "获取佛音列表（后台管理使用）")
	public DataResponse<List<BuddhistMusic>> adminList() {
		return new DataResponse<>(buddhistMusicService.adminList());
	}

}
