package com.wangbei.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 祈福明灯
 * 
 * @author luomengan
 *
 */
@Entity
@Table(name = "blessing_lamp")
public class BlessingLamp implements Serializable {

	private static final long serialVersionUID = -7134160579254738978L;

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
	 * 图片链接缩略图
	 */
	@Column(name = "small_link")
	private String smallLink;
	/**
	 * 寓意
	 */
	@Column(name = "meaning")
	private String meaning;
	/**
	 * 功效
	 */
	@Column(name = "effect")
	private String effect;
	/**
	 * 灯油列表
	 */
	@OneToMany(mappedBy = "lamp")
	private List<BlessingLampOil> oilList;

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

	public String getMeaning() {
		return meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}

	public String getEffect() {
		return effect;
	}

	public void setEffect(String effect) {
		this.effect = effect;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public List<BlessingLampOil> getOilList() {
		return oilList;
	}

	public void setOilList(List<BlessingLampOil> oilList) {
		this.oilList = oilList;
	}

	public String getSmallLink() {
		return smallLink;
	}

	public void setSmallLink(String smallLink) {
		this.smallLink = smallLink;
	}

}
