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

import org.joda.time.DateTime;

import com.wangbei.awre.converter.OfferingTypeEnumConverter;
import com.wangbei.util.enums.OfferingTypeEnum;

/**
 * @author yuyidi 2017-07-14 17:32:11
 * @class com.wangbei.entity.MeritDetail
 * @description 功德详情
 */
@Entity
@Table(name = "merit_detail")
public class MeritDetail implements Serializable {

	private static final long serialVersionUID = 8260338049970060897L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/**
	 * 用户id
	 */
	@Column(name = "user_id", nullable = false)
	private Integer userId;
	/**
	 * 佛id
	 */
	@Column(name = "joss_id")
	private Integer jossId;
	/**
	 * 供品id
	 */
	@Column(name = "offerings_id", nullable = false)
	private Integer offeringsId;
	/**
	 * 消耗功德数
	 */
	@Column(name = "merit_value")
	private Integer meritValue;
	/**
	 * 供品类型
	 */
	@Convert(converter = OfferingTypeEnumConverter.class)
	@Column(name = "type")
	private OfferingTypeEnum type;
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

	public Integer getOfferingsId() {
		return offeringsId;
	}

	public void setOfferingsId(Integer offeringsId) {
		this.offeringsId = offeringsId;
	}

	public Integer getMeritValue() {
		return meritValue;
	}

	public void setMeritValue(Integer meritValue) {
		this.meritValue = meritValue;
	}

	public OfferingTypeEnum getType() {
		return type;
	}

	public void setType(OfferingTypeEnum type) {
		this.type = type;
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

	public Integer getJossId() {
		return jossId;
	}

	public void setJossId(Integer jossId) {
		this.jossId = jossId;
	}

	public MeritDetail() {
	}

	public MeritDetail(Integer userId, Integer offeringsId, Integer meritValue, OfferingTypeEnum type) {
		this.userId = userId;
		this.offeringsId = offeringsId;
		this.meritValue = meritValue;
		this.type = type;
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
