package com.wangbei.entity;

import com.wangbei.awre.converter.DivinationTypeEnumConverter;
import com.wangbei.util.enums.DivinationTypeEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author yuyidi 2017-07-10 21:27:38
 * @class com.wangbei.entity.Divination
 * @description 灵签
 */
@Entity
@Table(name = "divination")
public class Divination implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "sequence_number")
	private String sequenceNumber;
	// 签诗
	@Column(name = "poem")
	private String poem;
	// 解签
	@Column(name = "solution")
	private String solution;
	@Column(name = "type")
	@Convert(converter = DivinationTypeEnumConverter.class)
	private DivinationTypeEnum type;
	// 引用
	@Column(name = "reference")
	private String reference;
	// 概述
	@Column(name = "summarize")
	private String summarize;
	// 签语
	@Column(name = "sketch")
	private String sketch;
	// 解签消耗的功德
	@Column(name = "merit_value")
	private int meritValue;

	@Transient
	private Date expireTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(String sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public String getPoem() {
		return poem;
	}

	public void setPoem(String poem) {
		this.poem = poem;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public DivinationTypeEnum getType() {
		return type;
	}

	public void setType(DivinationTypeEnum type) {
		this.type = type;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getSummarize() {
		return summarize;
	}

	public void setSummarize(String summarize) {
		this.summarize = summarize;
	}

	public String getSketch() {
		return sketch;
	}

	public void setSketch(String sketch) {
		this.sketch = sketch;
	}

	public int getMeritValue() {
		return meritValue;
	}

	public void setMeritValue(int meritValue) {
		this.meritValue = meritValue;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}
}
