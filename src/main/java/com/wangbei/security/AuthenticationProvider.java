package com.wangbei.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.wangbei.dao.UserDao;
import com.wangbei.entity.User;

public class AuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	@Autowired
	private UserDao userDao;

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		if (!userDetails.getPassword().equalsIgnoreCase(authentication.getCredentials().toString())) {
			throw new BadCredentialsException("密码错误");
		}
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		User user = userDao.fetchUserByPhone(username);
		if (user == null) {
			throw new UsernameNotFoundException("用户名不存在");
		}
		AuthUserDetails result = new AuthUserDetails(user.getPhone(), user.getPassword(), null);
		return result;
	}

}
