package com.wangbei.entity;

import com.wangbei.awre.converter.OfferingTypeEnumConverter;
import com.wangbei.util.enums.OfferingTypeEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author yuyidi 2017-07-10 21:41:42
 * @class com.wangbei.entity.Offerings
 * @description 供品
 */
@Entity
@Table(name = "Offerings")
public class Offerings implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "designation")
	private String designation;
	@Column(name = "type")
	@Convert(converter = OfferingTypeEnumConverter.class)
	private OfferingTypeEnum type;
	@Column(name = "merit_value")
	private Integer meritValue;
	@Column(name = "paraphrase")
	private String paraphrase;
	@Column(name = "link")
	private String link;
	@Column(name = "small_link")
	private String smallLink;
	@Transient
	private Date expireTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public OfferingTypeEnum getType() {
		return type;
	}

	public void setType(OfferingTypeEnum type) {
		this.type = type;
	}

	public Integer getMeritValue() {
		return meritValue;
	}

	public void setMeritValue(Integer meritValue) {
		this.meritValue = meritValue;
	}

	public String getParaphrase() {
		return paraphrase;
	}

	public void setParaphrase(String paraphrase) {
		this.paraphrase = paraphrase;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getSmallLink() {
		return smallLink;
	}

	public void setSmallLink(String smallLink) {
		this.smallLink = smallLink;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}
}
