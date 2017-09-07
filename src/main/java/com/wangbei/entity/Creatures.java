package com.wangbei.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 生灵
 * 
 * @author luomengan
 *
 */
@Entity
@Table(name = "creatures")
public class Creatures implements Serializable {

	private static final long serialVersionUID = 684299531573710119L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/**
	 * 名称
	 */
	@Column(name = "name")
	private String name;
	/**
	 * 图片链接
	 */
	@Column(name = "link")
	private String link;
	/**
	 * 消耗功德值
	 */
	@Column(name = "merit_value")
	private Integer meritValue;
	/**
	 * 消耗中文描述
	 */
	@Column(name = "merit")
	private String merit;
	/**
	 * 是否可用
	 */
	@Column(name = "is_enable")
	private Boolean isEnable;
	/**
	 * 排序
	 */
	@Column(name = "sort_num")
	private Integer sortNum;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Integer getMeritValue() {
		return meritValue;
	}

	public void setMeritValue(Integer meritValue) {
		this.meritValue = meritValue;
	}

	public String getMerit() {
		return merit;
	}

	public void setMerit(String merit) {
		this.merit = merit;
	}

	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

	public Boolean getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}

}
