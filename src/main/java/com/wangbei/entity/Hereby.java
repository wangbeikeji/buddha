package com.wangbei.entity;

import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author yuyidi 2017-07-14 10:58:42
 * @class com.wangbei.entity.Hereby
 * @description 恭请佛
 */
@Entity
@Table(name = "hereby")
public class Hereby implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "user_id", unique = true)
	private Integer userId;
	@Column(name = "joss_id")
	private Integer jossId;
	@Column(name = "create_time")
	private Date createTime;
	@Column(name = "expire_time")
	private Date expireTime;

	public Hereby() {
	}

	public Hereby(Integer userId, Integer jossId) {
		this.userId = userId;
		this.jossId = jossId;
	}

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

	public Integer getJossId() {
		return jossId;
	}

	public void setJossId(Integer jossId) {
		this.jossId = jossId;
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

	/**
	 * 设置过期时间
	 */
	public void expire() {
		DateTime time = new DateTime();
		setCreateTime(time.toDate());
		DateTime expire = time.plusHours(24);
		Date expireTime = expire.toDate();
		setExpireTime(expireTime);
	}
}
