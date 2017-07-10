package com.wangbei.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wangbei.dao.SysUserDao;
import com.wangbei.dao.impl.jpa.SysUserRepository;
import com.wangbei.entity.SysUser;

@Repository
public class SysUserDaoImpl implements SysUserDao {

	@Autowired
	private SysUserRepository sysUserRepository;

	@Override
	public SysUser findByUsername(String username) {
		return sysUserRepository.findByUsername(username);
	}

}
