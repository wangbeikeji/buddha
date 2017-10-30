package com.wangbei.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户点灯
 * 
 * @author luomengan
 *
 */
@Entity
@Table(name = "user_light_lamp")
public class UserLightLamp implements Serializable {

	private static final long serialVersionUID = -6190303879441480774L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/**
	 * 用户
	 */
	@Column(name = "user_id")
	private Integer userId;
	/**
	 * 祈福明灯id
	 */
	@Column(name = "lamp_id")
	private Integer lampId;
	/**
	 * 灯油id
	 */
	@Column(name = "oil_id")
	private Integer oilId;
	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;
	/**
	 * 过期时间
	 */
	@Column(name = "expire_time")
	private Date expireTime;
	/**
	 * 消耗的功德
	 */
	@Column(name = "merit_value")
	private int meritValue;

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

	public Integer getLampId() {
		return lampId;
	}

	public void setLampId(Integer lampId) {
		this.lampId = lampId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public int getMeritValue() {
		return meritValue;
	}

	public void setMeritValue(int meritValue) {
		this.meritValue = meritValue;
	}

	public Integer getOilId() {
		return oilId;
	}

	public void setOilId(Integer oilId) {
		this.oilId = oilId;
	}

}
