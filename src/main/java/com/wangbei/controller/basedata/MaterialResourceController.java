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

import com.wangbei.entity.MaterialResource;
import com.wangbei.pojo.DataResponse;
import com.wangbei.pojo.Response;
import com.wangbei.service.MaterialResourceService;
import com.wangbei.util.enums.MaterialResourceTypeEnum;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 素材资源 Controller
 * 
 * @author luomengan
 *
 */
@RestController
@RequestMapping("/materialResource")
@Api(description = "素材资源接口列表")
public class MaterialResourceController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public MaterialResourceService materialResourceService;

	@GetMapping("/{id}")
	@ApiOperation(value = "根据id获取素材资源")
	public Response<MaterialResource> fetchById(@PathVariable Integer id) {
		return new Response<>(materialResourceService.getMaterialResourceInfo(id));
	}

	@PostMapping("/")
	@ApiOperation(value = "添加素材资源")
	public Response<MaterialResource> addition(MaterialResource materialResource) {
		return new Response<>(materialResourceService.addMaterialResource(materialResource));
	}

	@PutMapping("/")
	@ApiOperation(value = "修改素材资源")
	public Response<MaterialResource> modification(MaterialResource materialResource) {
		return new Response<>(materialResourceService.modifyMaterialResource(materialResource));
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除素材资源")
	public Response<Integer> delete(@PathVariable Integer id) {
		materialResourceService.deleteMaterialResource(id);
		return new Response<Integer>(id);
	}

	@PostMapping("/deletes")
	@ApiOperation(value = "批量删除素材资源（多个id以逗号分割）")
	public Response<Boolean> deletes(String ids) {
		materialResourceService.deleteMaterialResources(ids);
		return new Response<Boolean>(true);
	}

	@GetMapping("/page")
	@ApiOperation(value = "获取素材资源分页数据")
	public Response<Page<MaterialResource>> materialResources(int page, int limit) {
		return new Response<>((Page<MaterialResource>) materialResourceService.materialResources(page, limit));
	}

	@GetMapping("/list")
	@ApiOperation(value = "获取素材资源列表")
	public Response<List<MaterialResource>> list() {
		return new Response<>(materialResourceService.list());
	}

	@GetMapping("/listByType")
	@ApiOperation(value = "根据类型获取素材资源列表")
	public Response<List<MaterialResource>> listByType(int type) {
		return new Response<>(materialResourceService.listByType(MaterialResourceTypeEnum.getByIndex(type)));
	}

	/******************************************* 以下方法为后台管理专用 **************************************************/

	@GetMapping("/adminList")
	@ApiOperation(value = "获取素材资源列表")
	public DataResponse<List<MaterialResource>> adminList() {
		return new DataResponse<>(materialResourceService.list());
	}

}
