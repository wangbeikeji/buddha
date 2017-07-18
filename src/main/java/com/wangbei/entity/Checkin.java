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
 * 签到
 * 
 * @author luomengan
 *
 */
@Entity
@Table(name = "checkin")
public class Checkin implements Serializable {

	private static final long serialVersionUID = 3664030378851104049L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * 用户id
	 */
	@Column(name = "user_id")
	private Integer userId;

	/**
	 * 签到时间
	 */
	@Column(name = "checkin_time")
	private Date checkinTime;

	/**
	 * 签到奖励的功德数
	 */
	@Column(name = "merit_value")
	private Integer meritValue;

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

	public Date getCheckinTime() {
		return checkinTime;
	}

	public void setCheckinTime(Date checkinTime) {
		this.checkinTime = checkinTime;
	}

	public Integer getMeritValue() {
		return meritValue;
	}

	public void setMeritValue(Integer meritValue) {
		this.meritValue = meritValue;
	}

}
