package com.wangbei.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.wangbei.dao.AdminUserDao;
import com.wangbei.dao.UserDao;
import com.wangbei.dao.UserLoginLogDao;
import com.wangbei.entity.AdminUser;
import com.wangbei.entity.User;
import com.wangbei.entity.UserLoginLog;
import com.wangbei.exception.ExceptionEnum;
import com.wangbei.exception.ServiceException;

public class AuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	@Autowired
	private UserDao userDao;

	@Autowired
	private AdminUserDao adminUserDao;

	@Autowired
	private UserLoginLogDao userLoginLogDao;

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		String[] passworArr = authentication.getCredentials().toString().split("_extra_");
		String phone = userDetails.getUsername();
		if (passworArr.length >= 1 && passworArr.length <= 2) {
			if (!userDetails.getPassword().equalsIgnoreCase(passworArr[0])) {
				throw new BadCredentialsException("密码错误");
			}
			if (passworArr.length == 2) {
				String newPhone = passworArr[1].trim();
				if (!userDetails.getUsername().equals(newPhone) && !"".equals(newPhone) && !"null".equals(newPhone)) {
					User check = userDao.fetchUserByPhone(newPhone);
					if (check != null) {
						throw new ServiceException(ExceptionEnum.LOGIN_ACCOUNT_OCCUPY_EXCEPTION);
					} else {
						User user = userDao.fetchUserByPhone(userDetails.getUsername());
						user.setPhone(newPhone);
						userDao.updateUser(user);
						phone = newPhone;
					}
				}
			}
		} else {
			throw new BadCredentialsException("密码错误");
		}

		// 保存登录日志
		AuthUserDetails customUserDetails = (AuthUserDetails) userDetails;
		if (!customUserDetails.isAdmin()) {
			UserLoginLog loginLog = new UserLoginLog();
			loginLog.setLoginTime(new Date());
			loginLog.setPhone(phone);
			loginLog.setUserId(customUserDetails.getUserId());
			userLoginLogDao.createUserLoginLog(loginLog);
		}
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		if (username != null && username.startsWith("admin_")) {
			username = username.substring(6);
			AdminUser user = adminUserDao.retrieveAdminUserByUsername(username);
			if (user == null) {
				throw new UsernameNotFoundException("用户名不存在");
			}
			AuthUserDetails result = new AuthUserDetails(user.getId(), user.getUsername(), user.getPassword(), null);
			result.setName(user.getName());
			result.setAdmin(true);
			return result;
		} else {
			User user = userDao.fetchUserByPhone(username);
			if (user == null) {
				throw new UsernameNotFoundException("用户名不存在");
			}
			AuthUserDetails result = new AuthUserDetails(user.getId(), user.getPhone(), user.getPassword(), null);
			result.setName(user.getName());
			return result;
		}
	}

}
