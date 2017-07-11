package com.wangbei.security;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.wangbei.dao.SysUserDao;
import com.wangbei.entity.SysUser;
import com.wangbei.util.Md5Util;

public class AuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	@Autowired
	private SysUserDao sysUserDao;

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		try {
			if (!userDetails.getPassword().equalsIgnoreCase(Md5Util.md5(authentication.getCredentials().toString()))) {
				throw new BadCredentialsException("密码错误");
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		SysUser user = sysUserDao.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("用户名不存在");
		}
		return user;
	}

	public SysUserDao getSysUserDao() {
		return sysUserDao;
	}

	public void setSysUserDao(SysUserDao sysUserDao) {
		this.sysUserDao = sysUserDao;
	}

}
