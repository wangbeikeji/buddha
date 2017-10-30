package com.wangbei.controller.userbehavior;

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

import com.wangbei.entity.BlessingLamp;
import com.wangbei.pojo.DataResponse;
import com.wangbei.pojo.Response;
import com.wangbei.pojo.UserLightLampInfo;
import com.wangbei.security.AuthUserDetails;
import com.wangbei.security.SecurityAuthService;
import com.wangbei.service.BlessingLampService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 祈福明灯 Controller
 * 
 * @author luomengan
 *
 */
@RestController
@RequestMapping("/blessingLamp")
@Api(description = "祈福明灯接口列表")
public class BlessingLampController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public BlessingLampService blessingLampService;

	@GetMapping("/{id}")
	@ApiOperation(value = "根据id获取祈福明灯")
	public Response<BlessingLamp> fetchById(@PathVariable Integer id) {
		return new Response<>(blessingLampService.getBlessingLampInfo(id));
	}

	@PostMapping("/")
	@ApiOperation(value = "添加祈福明灯")
	public Response<BlessingLamp> addition(BlessingLamp blessingLamp) {
		return new Response<>(blessingLampService.addBlessingLamp(blessingLamp));
	}

	@PutMapping("/")
	@ApiOperation(value = "修改祈福明灯")
	public Response<BlessingLamp> modification(BlessingLamp blessingLamp) {
		return new Response<>(blessingLampService.modifyBlessingLamp(blessingLamp));
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除祈福明灯")
	public Response<Integer> delete(@PathVariable Integer id) {
		blessingLampService.deleteBlessingLamp(id);
		return new Response<Integer>(id);
	}

	@PostMapping("/deletes")
	@ApiOperation(value = "批量删除祈福明灯（多个id以逗号分割）")
	public Response<Boolean> deletes(String ids) {
		blessingLampService.deleteBlessingLamps(ids);
		return new Response<Boolean>(true);
	}

	@GetMapping("/page")
	@ApiOperation(value = "获取祈福明灯分页数据")
	public Response<Page<BlessingLamp>> blessingLamps(int page, int limit) {
		return new Response<>((Page<BlessingLamp>) blessingLampService.blessingLamps(page, limit));
	}

	@GetMapping("/list")
	@ApiOperation(value = "获取祈福明灯列表")
	public DataResponse<List<BlessingLamp>> list() {
		return new DataResponse<>(blessingLampService.list());
	}

	@ApiOperation(value = "点灯")
	@PostMapping("/lightLamp")
	public Response<UserLightLampInfo> lightLamp(Integer lampId, Integer oilId) {
		AuthUserDetails authUserDetails = SecurityAuthService.getCurrentUser();
		return new Response<>(blessingLampService.lightLamp(authUserDetails.getUserId(), lampId, oilId));
	}

	@ApiOperation(value = "获取用户当前点灯信息")
	@GetMapping("/getCurrentLightLamp")
	public Response<UserLightLampInfo> getCurrentLightLamp() {
		AuthUserDetails authUserDetails = SecurityAuthService.getCurrentUser();
		return new Response<>(blessingLampService.getCurrentLightLamp(authUserDetails.getUserId()));
	}

}
