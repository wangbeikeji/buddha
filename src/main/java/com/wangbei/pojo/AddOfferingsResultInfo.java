package com.wangbei.pojo;

import java.util.Date;

import com.wangbei.util.enums.OfferingTypeEnum;

/**
 * 添加供品结果信息
 * 
 * @author luomengan
 *
 */
public class AddOfferingsResultInfo {

	/**
	 * 用户id
	 */
	private Integer userId;
	/**
	 * 佛id
	 */
	private Integer jossId;
	/**
	 * 供品id
	 */
	private Integer offeringsId;
	/**
	 * 供品名称
	 */
	private String offeringsDesignation;
	/**
	 * 供品寓意
	 */
	private String offeringsParaphrase;
	/**
	 * 供品图片链接
	 */
	private String offeringsLink;
	/**
	 * 供品小图链接
	 */
	private String offeringsSmallLink;
	/**
	 * 消耗功德数
	 */
	private Integer meritValue;
	/**
	 * 供品类型
	 */
	private OfferingTypeEnum type;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 过期时间
	 */
	private Date expireTime;

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

	public Integer getOfferingsId() {
		return offeringsId;
	}

	public void setOfferingsId(Integer offeringsId) {
		this.offeringsId = offeringsId;
	}

	public String getOfferingsDesignation() {
		return offeringsDesignation;
	}

	public void setOfferingsDesignation(String offeringsDesignation) {
		this.offeringsDesignation = offeringsDesignation;
	}

	public String getOfferingsParaphrase() {
		return offeringsParaphrase;
	}

	public void setOfferingsParaphrase(String offeringsParaphrase) {
		this.offeringsParaphrase = offeringsParaphrase;
	}

	public String getOfferingsLink() {
		return offeringsLink;
	}

	public void setOfferingsLink(String offeringsLink) {
		this.offeringsLink = offeringsLink;
	}

	public String getOfferingsSmallLink() {
		return offeringsSmallLink;
	}

	public void setOfferingsSmallLink(String offeringsSmallLink) {
		this.offeringsSmallLink = offeringsSmallLink;
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

}
