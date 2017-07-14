package com.wangbei.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author yuyidi 2017-07-10 21:24:10
 * @class com.wangbei.entity.Rune
 * @description 符文
 */
@Entity
@Table(name = "rune")
public class Rune implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "symbol")
	private String symbol;
	@Column(name = "effect")
	private String effect;
	@Column(name = "apply")
	private String apply;
	@Column(name = "link")
	private String link;
	@Column(name = "merit_value")
	private Integer meritValue;

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

}
