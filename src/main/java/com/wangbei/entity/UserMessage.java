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

import com.wangbei.awre.converter.PushTypeEnumConverter;
import com.wangbei.util.enums.PushTypeEnum;

/**
 * 用户消息
 * 
 * @author luomengan
 *
 */
@Entity
@Table(name = "user_message")
public class UserMessage implements Serializable {

	private static final long serialVersionUID = 1821474634673139721L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/**
	 * 用户id
	 */
	@Column(name = "user_id")
	private Integer userId;
	/**
	 * 标题
	 */
	@Column(name = "title")
	private String title;
	/**
	 * 提示
	 */
	@Column(name = "alert")
	private String alert;
	/**
	 * 推送类型
	 */
	@Column(name = "type")
	@Convert(converter = PushTypeEnumConverter.class)
	private PushTypeEnum type;
	/**
	 * 推送资源id
	 */
	@Column(name = "resource_id")
	private Integer resourceId;
	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAlert() {
		return alert;
	}

	public void setAlert(String alert) {
		this.alert = alert;
	}

	public PushTypeEnum getType() {
		return type;
	}

	public void setType(PushTypeEnum type) {
		this.type = type;
	}

	public Integer getResourceId() {
		return resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
