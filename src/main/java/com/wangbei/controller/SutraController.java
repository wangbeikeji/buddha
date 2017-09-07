package com.wangbei.controller;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import org.apache.commons.io.IOUtils;
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
import com.wangbei.pojo.DataResponse;
import com.wangbei.pojo.Response;
import com.wangbei.service.SutraService;
import com.wangbei.util.enums.SutraTypeEnum;

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

	@PostMapping("/deletes")
	@ApiOperation(value = "批量删除经书（多个id以逗号分割）")
	public Response<Boolean> deletes(String ids) {
		String[] idsArr = ids.split(",");
		for (String id : idsArr) {
			sutraService.deleteSutra(Integer.parseInt(id));
		}
		return new Response<Boolean>(true);
	}

	@GetMapping("/page")
	@ApiOperation(value = "获取经书分页数据")
	public Response<Page<Sutra>> sutras(int page, int limit) {
		return new Response<>((Page<Sutra>) sutraService.sutrasByIsEnableOrderById(true, page, limit));
	}

	@GetMapping("/pageByType")
	@ApiOperation(value = "根据类型获取经书分页数据（1:经文,2:咒语,3:禅宗,4:译文）")
	public Response<Page<Sutra>> sutrasByType(int type, int page, int limit) {
		return new Response<>((Page<Sutra>) sutraService
				.sutrasByTypeAndIsEnableOrderById(SutraTypeEnum.getByIndex(type), true, page, limit));
	}

	@GetMapping("/list")
	@ApiOperation(value = "获取经书列表")
	public Response<List<Sutra>> list() {
		return new Response<>(sutraService.listByIsEnableOrderById(true));
	}

	/******************************************* 以下方法为后台管理专用 **************************************************/

	@PostMapping("/adminAdd")
	@ApiOperation(value = "添加经书（后台管理使用）")
	public Response<Sutra> adminAdd(Sutra sutra) {
		return new Response<>(sutraService.adminAddSutra(sutra));
	}

	@GetMapping("/adminDtListOrderById")
	@ApiOperation(value = "获取经书列表（后台管理使用）")
	public DataResponse<List<Sutra>> adminDtListOrderById() {
		return new DataResponse<>(sutraService.listOrderById());
	}

	@GetMapping("/adminDtListByTypeOrderById")
	@ApiOperation(value = "获取经书列表（后台管理使用）")
	public DataResponse<List<Sutra>> adminDtListByTypeOrderById(int type) {
		return new DataResponse<>(sutraService.listByTypeOrderById(SutraTypeEnum.getByIndex(type)));
	}

	@GetMapping("/adminDtListByIsEnableOrderById")
	@ApiOperation(value = "获取经书列表（后台管理使用）")
	public DataResponse<List<Sutra>> adminDtListByIsEnableOrderById(boolean isEnable) {
		return new DataResponse<>(sutraService.listByIsEnableOrderById(isEnable));
	}

	@GetMapping("/adminListOrderById")
	@ApiOperation(value = "获取经书列表（后台管理使用）")
	public Response<List<Sutra>> adminListOrderById() {
		return new Response<>(sutraService.listOrderById());
	}

	@GetMapping("/adminListByIsEnableOrderById")
	@ApiOperation(value = "获取经书列表（后台管理使用）")
	public Response<List<Sutra>> adminListByIsEnableOrderById(boolean isEnable) {
		return new Response<>(sutraService.listByIsEnableOrderById(isEnable));
	}

	@GetMapping("/adminGetTxtContent")
	@ApiOperation(value = "获取txt内容")
	public String getTxtContent(String httpUrl) throws Exception {
		URL url = new URL(httpUrl);
		url.openConnection();
		InputStream is = url.openStream();
		String result = IOUtils.toString(is, "UTF-8");
		is.close();
		return result;
	}

	@PostMapping("/adminCollectData")
	@ApiOperation(value = "采集经书数据（后台管理使用）", notes = "type:1表示本地采集，2表示从http://www.liaotuo.org/fojing/采集，3表示从http://fjyw.foxue.org/采集")
	public Response<String> collectData(int type, String resource) {
		sutraService.collectSutraData(type, resource);
		return new Response<>("正在采集经书数据");
	}

}
