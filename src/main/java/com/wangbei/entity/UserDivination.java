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
 * 
 * 用户求签记录
 * 
 * @author luomengan
 *
 */
@Entity
@Table(name = "user_divination")
public class UserDivination implements Serializable {

	private static final long serialVersionUID = -4707852749905394388L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * 用户
	 */
	@Column(name = "user_id")
	private Integer userId;

	/**
	 * 签
	 */
	@Column(name = "divination_id")
	private Integer divinationId;

	/**
	 * 摇签时间
	 */
	@Column(name = "shake_time")
	private Date shakeTime;

	/**
	 * 解签时间
	 */
	@Column(name = "explain_time")
	private Date explainTime;

	/**
	 * 解签消耗的功德
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

	public Integer getDivinationId() {
		return divinationId;
	}

	public void setDivinationId(Integer divinationId) {
		this.divinationId = divinationId;
	}

	public Date getShakeTime() {
		return shakeTime;
	}

	public void setShakeTime(Date shakeTime) {
		this.shakeTime = shakeTime;
	}

	public Date getExplainTime() {
		return explainTime;
	}

	public void setExplainTime(Date explainTime) {
		this.explainTime = explainTime;
	}

	public int getMeritValue() {
		return meritValue;
	}

	public void setMeritValue(int meritValue) {
		this.meritValue = meritValue;
	}

}
