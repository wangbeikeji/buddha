package com.wangbei.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 灯油
 * 
 * @author luomengan
 *
 */
@Entity
@Table(name = "blessing_lamp_oil")
public class BlessingLampOil implements Serializable {

	private static final long serialVersionUID = 4820372622764898530L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/**
	 * 灯油描述
	 */
	@Column(name = "oil_desc")
	private String oilDesc;
	/**
	 * 灯油可燃烧天数
	 */
	@Column(name = "flame_days")
	private Integer flameDays;
	/**
	 * 需消耗的功德数
	 */
	@Column(name = "merit_value")
	private Integer meritValue;
	/**
	 * 对应的祈福明灯
	 */
	@ManyToOne
	@JoinColumn(name = "lamp_id")
	private BlessingLamp lamp;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOilDesc() {
		return oilDesc;
	}

	public void setOilDesc(String oilDesc) {
		this.oilDesc = oilDesc;
	}

	public Integer getFlameDays() {
		return flameDays;
	}

	public void setFlameDays(Integer flameDays) {
		this.flameDays = flameDays;
	}

	public Integer getMeritValue() {
		return meritValue;
	}

	public void setMeritValue(Integer meritValue) {
		this.meritValue = meritValue;
	}

}
