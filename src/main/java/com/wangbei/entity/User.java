package com.wangbei.entity;

import com.wangbei.awre.converter.GenderEnumConverter;
import com.wangbei.util.enums.GenderEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author yuyidi 2017-07-06 16:52:01
 * @class com.wangbei.entity.User
 * @description 用户
 */
@ApiModel
@Entity
@Table(name = "user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/**
	 * 姓名
	 */
	@Column(name = "name")
	private String name;
	/**
	 * 手机号码
	 */
	@Column(name = "phone", unique = true, nullable = false)
	private String phone;
	/**
	 * 密码
	 */
	@Column(name = "password", nullable = false)
	private String password;
	/**
	 * 性别
	 */
	@ApiModelProperty(allowableValues = "1,2", dataType = "integer")
	@Convert(converter = GenderEnumConverter.class)
	@Column(name = "gender")
	private GenderEnum gender = GenderEnum.MAN;
	/**
	 * 生日
	 */
	@Column(name = "birthday")
	private String birthday;
	/**
	 * 头像
	 */
	@Column(name = "head_portrait_link")
	private String headPortraitLink;
	/**
	 * 地址
	 */
	@Column(name = "address")
	private String address;
	/**
	 * 签名
	 */
	@Column(name = "signature")
	private String signature;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(hidden = true)
	@Column(name = "create_time")
	private Date creatTime = new Date();

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public GenderEnum getGender() {
		return gender;
	}

	public void setGender(GenderEnum gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public Date getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHeadPortraitLink() {
		return headPortraitLink;
	}

	public void setHeadPortraitLink(String headPortraitLink) {
		this.headPortraitLink = headPortraitLink;
	}

}
