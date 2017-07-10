package com.wangbei.dao;

import com.wangbei.entity.SysUser;

public interface SysUserDao {

	SysUser findByUsername(String username);

}
