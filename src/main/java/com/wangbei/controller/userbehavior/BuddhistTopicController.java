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

import com.wangbei.entity.BuddhistTopic;
import com.wangbei.pojo.BuddhistTopicFullInfo;
import com.wangbei.pojo.DataResponse;
import com.wangbei.pojo.Response;
import com.wangbei.pojo.UserCommentInfo;
import com.wangbei.security.AuthUserDetails;
import com.wangbei.security.SecurityAuthService;
import com.wangbei.service.BuddhistTopicService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 修行主题 Controller
 * 
 * @author luomengan
 *
 */
@RestController
@RequestMapping("/buddhistTopic")
@Api(description = "修行主题接口列表")
public class BuddhistTopicController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public BuddhistTopicService buddhistTopicService;

	@GetMapping("/{id}")
	@ApiOperation(value = "根据id获取修行主题")
	public Response<BuddhistTopic> fetchById(@PathVariable Integer id) {
		return new Response<>(buddhistTopicService.getBuddhistTopicInfo(id));
	}

	@PostMapping("/")
	@ApiOperation(value = "添加修行主题")
	public Response<BuddhistTopic> addition(BuddhistTopic buddhistTopic) {
		return new Response<>(buddhistTopicService.addBuddhistTopic(buddhistTopic));
	}

	@PutMapping("/")
	@ApiOperation(value = "修改修行主题")
	public Response<BuddhistTopic> modification(BuddhistTopic buddhistTopic) {
		return new Response<>(buddhistTopicService.modifyBuddhistTopic(buddhistTopic));
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除修行主题")
	public Response<Integer> delete(@PathVariable Integer id) {
		buddhistTopicService.deleteBuddhistTopic(id);
		return new Response<Integer>(id);
	}

	@PostMapping("/deletes")
	@ApiOperation(value = "批量删除修行主题（多个id以逗号分割）")
	public Response<Boolean> deletes(String ids) {
		buddhistTopicService.deleteBuddhistTopics(ids);
		return new Response<Boolean>(true);
	}

	@GetMapping("/page")
	@ApiOperation(value = "获取修行主题分页数据")
	public Response<Page<BuddhistTopicFullInfo>> buddhistTopicsByIsEnable(int page, int limit) {
		return new Response<>(buddhistTopicService.buddhistTopicsByIsEnable(true, page, limit));
	}

	@PostMapping("/userLike")
	@ApiOperation(value = "用户点赞修行主题")
	public Response<Integer> userLike(Integer id) {
		AuthUserDetails authUserDetails = SecurityAuthService.getCurrentUser();
		return new Response<>(buddhistTopicService.userLike(authUserDetails.getUserId(), id));
	}

	@PostMapping("/userLikeComment")
	@ApiOperation(value = "用户点赞修行主题评论")
	public Response<Integer> userLikeComment(Integer commentId) {
		return new Response<>(buddhistTopicService.userLikeComment(SecurityAuthService.getUserId(), commentId));
	}

	@PostMapping("/userComment")
	@ApiOperation(value = "用户评论修行主题")
	public Response<UserCommentInfo> userComment(Integer id, String comment) {
		return new Response<>(buddhistTopicService.userComment(SecurityAuthService.getUserId(), id, comment));
	}

	@GetMapping("/getCountLike")
	@ApiOperation(value = "获取某个修行主题的点赞总数")
	public Response<Integer> getCountLike(Integer id) {
		return new Response<>(buddhistTopicService.getCountLike(id));
	}

	@GetMapping("/getBuddhistTopicFullInfo")
	@ApiOperation(value = "根据id获取修行主题详情页全部信息")
	public Response<BuddhistTopicFullInfo> getBuddhistTopicFullInfo(Integer id, int page, int limit) {
		AuthUserDetails authUserDetails = SecurityAuthService.getCurrentUser();
		return new Response<>(
				buddhistTopicService.getBuddhistTopicFullInfo(authUserDetails.getUserId(), id, page, limit));
	}

	/******************************************* 以下方法为后台管理专用 **************************************************/

	@GetMapping("/adminList")
	@ApiOperation(value = "获取修行主题列表")
	public DataResponse<List<BuddhistTopic>> adminList() {
		return new DataResponse<>(buddhistTopicService.list());
	}

}
