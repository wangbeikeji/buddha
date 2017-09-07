package com.wangbei.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.wangbei.awre.converter.BindingTypeEnumConverter;
import com.wangbei.util.enums.BindingTypeEnum;

/**
 * 用户绑定
 * 
 * @author luomengan
 *
 */
@Entity
@Table(name = "user_binding")
public class UserBinding implements Serializable {

	private static final long serialVersionUID = -7926736107010619126L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/**
	 * 用户id
	 */
	@Column(name = "user_id")
	private Integer userId;
	/**
	 * 用户绑定类型
	 */
	@Column(name = "type")
	@Convert(converter = BindingTypeEnumConverter.class)
	private BindingTypeEnum type;
	/**
	 * 绑定账号
	 */
	@Column(name = "binding_account")
	private String bindingAccount;
	/**
	 * 绑定时间
	 */
	@Column(name = "binging_time")
	private Date bindingTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public BindingTypeEnum getType() {
		return type;
	}

	public void setType(BindingTypeEnum type) {
		this.type = type;
	}

	public String getBindingAccount() {
		return bindingAccount;
	}

	public void setBindingAccount(String bindingAccount) {
		this.bindingAccount = bindingAccount;
	}

	public Date getBindingTime() {
		return bindingTime;
	}

	public void setBindingTime(Date bindingTime) {
		this.bindingTime = bindingTime;
	}

}
