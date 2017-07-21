package com.wangbei.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wangbei.entity.Beg;
import com.wangbei.entity.Checkin;
import com.wangbei.entity.Divination;
import com.wangbei.entity.FreeLife;
import com.wangbei.entity.Hereby;
import com.wangbei.entity.Joss;
import com.wangbei.entity.MeritDetail;
import com.wangbei.entity.Offerings;
import com.wangbei.entity.Rune;
import com.wangbei.entity.Trade;
import com.wangbei.entity.User;
import com.wangbei.pojo.JossSurrounding;
import com.wangbei.pojo.Response;
import com.wangbei.pojo.TradeWithUserMeritValue;
import com.wangbei.pojo.UserShakeDivinationInfo;
import com.wangbei.pojo.UserWithToken;
import com.wangbei.pojo.ValidateCode;
import com.wangbei.security.AuthUserDetails;
import com.wangbei.security.SecurityAuthService;
import com.wangbei.security.jwt.TokenAuthenticationService;
import com.wangbei.service.BegService;
import com.wangbei.service.CheckinService;
import com.wangbei.service.FreeLifeService;
import com.wangbei.service.HerebyService;
import com.wangbei.service.JossService;
import com.wangbei.service.MeritDetailService;
import com.wangbei.service.OfferingsService;
import com.wangbei.service.RuneService;
import com.wangbei.service.UserDivinationService;
import com.wangbei.service.UserService;
import com.wangbei.util.SafeCollectionUtil;
import com.wangbei.util.enums.CreatureEnum;
import com.wangbei.util.enums.OfferingTypeEnum;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * @author yuyidi 2017-07-06 17:39:59
 * @class com.wangbei.controller.UserController
 * @description 用户控制器
 */
@RestController
@RequestMapping("/user")
@Api(description = "用户相关接口列表")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private HerebyService herebyService;
	@Autowired
	private OfferingsService offeringsService;
	@Autowired
	private MeritDetailService meritDetailService;
	@Autowired
	private JossService jossService;
	@Autowired
	private RuneService runeService;
	@Autowired
	private BegService begService;
	@Autowired
	private FreeLifeService freeLifeService;
	@Autowired
	private UserDivinationService userDivinationService;
	@Autowired
	private CheckinService checkinService;

	@PostMapping("/register")
	@ApiOperation(value = "用户注册")
	public Response<UserWithToken> addition(User user, Integer validateCode) {
		Response<UserWithToken> response = null;
		if (user.getPhone() != null) {
			ValidateCode validate = SafeCollectionUtil.getValidateCode(user.getPhone());
			if (validate != null && validate.getCode().equals(validateCode)) {
				User userInfo = userService.addUser(user);
				UserWithToken result = new UserWithToken(userInfo);
				result.setMeritValue(userService.getUserMeritValue(userInfo.getId()));
				result.setToken(TokenAuthenticationService.generateToken(userInfo.getId(), userInfo.getPhone(), ""));
				response = new Response<>(result);
				return response;
			}
			return new Response<>("3000", "用户验证码发送失败");
		}
		return new Response<>("4001", "手机号不能为空");
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "完善用户信息")
	public Response<User> complete(User user) {
		return new Response<>(userService.modifyUser(user));
	}

	@GetMapping("/getCurrent")
	@ApiOperation(value = "获取当前用户信息")
	public Response<UserWithToken> getCurrent() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserWithToken result = new UserWithToken(userService.getUserByPhone(auth.getName()));
		if (result.getId() > 0) {
			result.setMeritValue(userService.getUserMeritValue(result.getId()));
		}
		return new Response<>(result);
	}

	@PutMapping("/resetPassword")
	@ApiOperation(value = "重置密码")
	public Response<UserWithToken> resetPassword(String phone, Integer validateCode, String password) {
		Response<UserWithToken> response = null;
		if (phone != null) {
			ValidateCode validate = SafeCollectionUtil.getValidateCode(phone);
			if (validate != null && validate.getCode().equals(validateCode)) {
				User userInfo = userService.resetPassword(phone, password);
				UserWithToken result = new UserWithToken(userInfo);
				result.setMeritValue(userService.getUserMeritValue(userInfo.getId()));
				result.setToken(TokenAuthenticationService.generateToken(userInfo.getId(), userInfo.getPhone(), ""));
				response = new Response<>(result);
				return response;
			}
			return new Response<>("3000", "用户验证码发送失败");
		}
		return new Response<>("4001", "手机号不能为空");
	}

	@ApiOperation(value = "请佛")
	@PostMapping("/{id}/hereby/")
	public Response<Hereby> additionHereby(@PathVariable Integer id, Integer joss) {
		Response<Hereby> response = new Response<>();
		AuthUserDetails authUserDetails = SecurityAuthService.getCurrentUser();
		if (authUserDetails.getUserId() == id) {
			Hereby hereby = herebyService.addHereby(authUserDetails.getUserId(), joss);
			if (hereby != null) {
				response = new Response<>(hereby);
				return response;
			}
			response.setCode("2003");
			response.setMessage("恭请佛未成功");
		}
		// 若当前用户与请求的用户不相同
		response.setCode("2003");
		response.setMessage("当前用户信息不匹配");
		return response;
	}

	@Deprecated
	@ApiOperation(value = "恭请其他佛", hidden = true)
	@PutMapping("/{id}/hereby/{hereby}")
	public Response<Hereby> modificationHereby(@PathVariable Integer id, @PathVariable Integer hereby, Integer joss) {
		Response<Hereby> response = new Response<>();
		AuthUserDetails authUserDetails = SecurityAuthService.getCurrentUser();
		if (authUserDetails.getUserId() == id) {
			Hereby result = herebyService.modifyHereby(hereby, authUserDetails.getUserId(), joss);
			if (result != null) {
				response.setResult(result);
				return response;
			}
		}
		// 若当前用户与请求的用户不相同
		response.setCode("2003");
		response.setMessage("当前用户信息不匹配");
		return response;
	}

	@ApiOperation(value = "用户添加供品")
	@PostMapping("/{id}/offerings/")
	public Response<MeritDetail> offerings(@PathVariable Integer id, Integer offerings) {
		Response<MeritDetail> response = new Response<>();
		AuthUserDetails authUserDetails = SecurityAuthService.getCurrentUser();
		if (authUserDetails.getUserId() == id) {
			Offerings offeringsInfo = offeringsService.getOfferingsInfo(offerings);
			if (offeringsInfo != null) {
				// 供品信息存在 添加供品功德详情
				MeritDetail meritDetail = meritDetailService.addMeritDetail(id, offeringsInfo);
				if (meritDetail != null) {
					response.setResult(meritDetail);
				}
				return response;
			}
		}
		// 若当前用户与请求的用户不相同
		response.setCode("2003");
		response.setMessage("当前用户信息不匹配");
		return response;
	}

	@ApiOperation(value = "求符")
	@PostMapping("/{id}/beg/")
	public Response<Beg> additionBeg(@PathVariable Integer id, Integer rune) {
		Response<Beg> response = new Response<>();
		AuthUserDetails authUserDetails = SecurityAuthService.getCurrentUser();
		if (authUserDetails.getUserId() == id) {
			Rune runeInfo = runeService.getRuneInfo(rune);
			if (runeInfo != null) {
				Beg beg = begService.addBeg(authUserDetails.getUserId(), rune, runeInfo.getMeritValue());
				if (beg != null) {
					response = new Response<>(beg);
					return response;
				}
			}
			response.setCode("2003");
			response.setMessage("求符失败");

		}
		// 若当前用户与请求的用户不相同
		response.setCode("2003");
		response.setMessage("当前用户信息不匹配");
		return response;
	}

	/**
	 * @author yuyidi 2017-07-14 21:10:26
	 * @class com.wangbei.controller.UserController
	 * @description 根据用户获取用户对应的佛与供品信息
	 */
	@ApiOperation(value = "用户请佛及供品信息")
	@GetMapping("/{id}/joss/")
	public Response<JossSurrounding> jossOfferings(@PathVariable Integer id) {
		Response<JossSurrounding> response = new Response<>();
		AuthUserDetails authUserDetails = SecurityAuthService.getCurrentUser();
		if (authUserDetails.getUserId() == id) {
			// 根据用户获取用户请佛信息
			Joss joss = jossService.getJossByUser(id);
			if (joss != null) {
				// 获取用户供品信息
				Map<String, Offerings> offerings = offeringsService.getOfferingsByUser(id);
				// 获取用户的求符信息
				List<Rune> runes = runeService.getRuneByUser(id);
				JossSurrounding jossSurrounding = new JossSurrounding();
				jossSurrounding.setJoss(joss);
				jossSurrounding.setOfferings(offerings);
				jossSurrounding.setRunes(runes);
				response.setResult(jossSurrounding);
			}
		}
		return response;
	}

	@ApiOperation(value = "获取供品功德详情")
	@ApiImplicitParam(name = "type", allowableValues = "1,2,3", paramType = "query", dataType = "int")
	@GetMapping("/{id}/meritdetail")
	public Response<MeritDetail> meritDetail(@PathVariable Integer id, @RequestParam OfferingTypeEnum type) {
		return new Response<>(meritDetailService.getByUserAndType(id, type));
	}

	@ApiOperation(value = "用户摇签")
	@PostMapping("/{id}/shakeDivination")
	public Response<UserShakeDivinationInfo> shakeDivination(@PathVariable Integer id) {
		return new Response<>(userDivinationService.shakeDivination(id));
	}

	@ApiOperation(value = "用户解签")
	@PutMapping("/{id}/explainDivination")
	public Response<Divination> explainDivination(@PathVariable Integer id, @RequestParam Integer userDivinationId) {
		return new Response<>(userDivinationService.explainDivination(id, userDivinationId));
	}

	@ApiOperation(value = "用户签到")
	@PutMapping("/{id}/checkin")
	public Response<Checkin> checkin(@PathVariable Integer id) {
		return new Response<>(checkinService.checkin(id));
	}

	/**
	 * @param
	 * @return com.wangbei.pojo.Response<java.lang.String>
	 * @author yuyidi 2017-07-17 15:51:08
	 * @method charge
	 * @description 充值
	 */
	@ApiOperation(value = "账户充值（type：0充值 7放生）")
	@PostMapping("/{id}/charge/")
	public Response<Trade> charge(@PathVariable Integer id, Integer meritValue, Integer type) {
		Response<Trade> response = new Response<>();
		AuthUserDetails authUserDetails = SecurityAuthService.getCurrentUser();
		if (authUserDetails.getUserId() == id) {
			response.setResult(userService.charge(id, meritValue, type));
			return response;
		}
		// 若当前用户与请求的用户不相同
		response.setCode("2003");
		response.setMessage("当前用户信息不匹配");
		return response;
	}
	
	@ApiOperation(value = "验证充值是否完成")
	@PostMapping("/validateCharge/")
	public Response<TradeWithUserMeritValue> validateCharge(String tradeNo) {
		return new Response<>(userService.validateCharge(tradeNo));
	}

	/**
	 * @author yuyidi 2017-07-18 14:54:15
	 * @method freeLife
	 * @param id
	 * @param creature
	 * @param meritValue
	 * @return com.wangbei.pojo.Response<java.lang.String>
	 * @description 放生
	 */
	@ApiOperation(value = "放生（1千年龟，2百灵鸟，3锦鲤）")
	@ApiImplicitParam(name = "creature", allowableValues = "1,2,3", paramType = "query", dataType = "int")
	@PostMapping("/{id}/freelife")
	public Response<String> freeLife(@PathVariable Integer id, @RequestParam CreatureEnum creature, @RequestParam(required = false) String tradeNo) {
		Response<String> response = new Response<>();
		AuthUserDetails authUserDetails = SecurityAuthService.getCurrentUser();
		if (authUserDetails.getUserId() == id) {
			FreeLife result = freeLifeService.addFreeLife(id, creature, tradeNo);
			if (result != null) {
				response.setResult("功德无量");
			}
			return response;
		}
		// 若当前用户与请求的用户不相同
		response.setCode("2003");
		response.setMessage("当前用户信息不匹配");
		return response;
	}
}
