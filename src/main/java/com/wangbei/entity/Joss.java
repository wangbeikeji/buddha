package com.wangbei.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author yuyidi 2017-07-10 21:35:18
 * @class com.wangbei.entity.Joss
 * @description 佛信息
 */
@Entity
@Table(name = "joss")
public class Joss implements Serializable {

	private static final long serialVersionUID = 3649999469476411856L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "buddha_name")
	private String buddhaName;
	@Column(name = "link")
	private String link;
	@Column(name = "introduction")
	private String introduction;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBuddhaName() {
		return buddhaName;
	}

	public void setBuddhaName(String buddhaName) {
		this.buddhaName = buddhaName;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
}
