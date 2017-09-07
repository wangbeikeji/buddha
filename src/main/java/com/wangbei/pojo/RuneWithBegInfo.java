package com.wangbei.pojo;

import com.wangbei.entity.Beg;
import com.wangbei.entity.Rune;

public class RuneWithBegInfo extends Rune {

	/**
	 * 是否为自己请符
	 * 
	 * <ul>
	 * <li>true为自己请符</li>
	 * <li>false为他人请符</li>
	 * </ul>
	 */
	private Boolean isSelf = true;
	/**
	 * 他人姓名
	 */
	private String otherName;
	/**
	 * 他人性别
	 * 
	 * <ul>
	 * <li>0男</li>
	 * <li>1女</li>
	 * </ul>
	 */
	private Integer otherGender;
	/**
	 * 他人生日
	 */
	private String otherBirthday;

	private static final long serialVersionUID = 3797330385015840175L;

	public RuneWithBegInfo(Rune rune, Beg beg) {
		this.setApply(rune.getApply());
		this.setCreateTime(beg.getCreateTime());
		this.setEffect(rune.getEffect());
		this.setExpireTime(beg.getExpireTime());
		this.setId(rune.getId());
		this.setIsSelf(beg.getIsSelf());
		this.setLink(rune.getLink());
		this.setMeritValue(rune.getMeritValue());
		this.setOtherBirthday(beg.getOtherBirthday());
		this.setOtherGender(beg.getOtherGender());
		this.setOtherName(beg.getOtherName());
		this.setSymbol(rune.getSymbol());
	}

	public Boolean getIsSelf() {
		return isSelf;
	}

	public void setIsSelf(Boolean isSelf) {
		this.isSelf = isSelf;
	}

	public String getOtherName() {
		return otherName;
	}

	public void setOtherName(String otherName) {
		this.otherName = otherName;
	}

	public Integer getOtherGender() {
		return otherGender;
	}

	public void setOtherGender(Integer otherGender) {
		this.otherGender = otherGender;
	}

	public String getOtherBirthday() {
		return otherBirthday;
	}

	public void setOtherBirthday(String otherBirthday) {
		this.otherBirthday = otherBirthday;
	}

}
