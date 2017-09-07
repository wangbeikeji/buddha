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
 * @author yuyidi 2017-07-14 10:58:42
 * @class com.wangbei.entity.Hereby
 * @description 恭请佛
 */
@Entity
@Table(name = "hereby")
public class Hereby implements Serializable {

	private static final long serialVersionUID = 783974344140703786L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "user_id")
	private Integer userId;
	@Column(name = "joss_id")
	private Integer jossId;
	@Column(name = "create_time")
	private Date createTime = new Date();
	@Column(name = "sort_num")
	private Integer sortNum;

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

	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

}
