package com.wangbei.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 后台管理用户
 * 
 * @author luomengan
 *
 */
@Entity
@Table(name = "admin_user")
public class AdminUser implements Serializable {

	private static final long serialVersionUID = 8363427529010251243L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/**
	 * 姓名
	 */
	@Column(name = "name")
	private String name;
	/**
	 * 用户名
	 */
	@Column(name = "username", unique = true, nullable = false)
	private String username;
	/**
	 * 密码
	 */
	@Column(name = "password", nullable = false)
	private String password;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
