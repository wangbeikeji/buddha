package com.wangbei.dao.impl.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wangbei.entity.SysUser;

public interface SysUserRepository extends JpaRepository<SysUser, Long> {

	SysUser findByUsername(String username);

}
