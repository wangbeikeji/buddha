package com.wangbei.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author yuyidi 2017-07-10 21:24:10
 * @class com.wangbei.entity.Rune
 * @description 符文
 */
@Entity
@Table(name = "rune")
public class Rune implements Serializable {

	private static final long serialVersionUID = -7994807073300712620L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/**
	 * 符文名称
	 */
	@Column(name = "symbol")
	private String symbol;
	/**
	 * 功效
	 */
	@Column(name = "effect")
	private String effect;
	/**
	 * 适用
	 */
	@Column(name = "apply")
	private String apply;
	/**
	 * 符图片链接
	 */
	@Column(name = "link")
	private String link;
	/**
	 * 消耗功德数
	 */
	@Column(name = "merit_value")
	private Integer meritValue;
	
	@Transient
	private Date createTime;
	@Transient
	private Date expireTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getEffect() {
		return effect;
	}

	public void setEffect(String effect) {
		this.effect = effect;
	}

	public String getApply() {
		return apply;
	}

	public void setApply(String apply) {
		this.apply = apply;
	}

	public Integer getMeritValue() {
		return meritValue;
	}

	public void setMeritValue(Integer meritValue) {
		this.meritValue = meritValue;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
