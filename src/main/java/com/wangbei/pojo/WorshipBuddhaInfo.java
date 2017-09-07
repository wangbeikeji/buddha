package com.wangbei.pojo;

import java.util.Date;
import java.util.List;

/**
 * 拜佛信息
 * 
 * <p>
 * 包括佛、花、香、果、符文信息
 * </p>
 * 
 * @author luomengan
 *
 */
public class WorshipBuddhaInfo {

	/**
	 * 佛id
	 */
	private Integer jossId;
	/**
	 * 佛名称
	 */
	private String jossBuddhaName;
	/**
	 * 佛图片链接
	 */
	private String jossLink;
	/**
	 * 佛介绍
	 */
	private String jossIntroduction;
	/**
	 * 请佛时间
	 */
	private Date jossCreateTime;
	/**
	 * 香id
	 */
	private Integer sandalwoodId;
	/**
	 * 香名称
	 */
	private String sandalwoodDesignation;
	/**
	 * 香寓意
	 */
	private String sandalwoodParaphrase;
	/**
	 * 香图片链接
	 */
	private String sandalwoodLink;
	/**
	 * 香小图链接
	 */
	private String sandalwoodSmallLink;
	/**
	 * 香创建时间
	 */
	private Date sandalwoodCreateTime;
	/**
	 * 香过期时间
	 */
	private Date sandalwoodExpireTime;
	/**
	 * 果id
	 */
	private Integer fruitId;
	/**
	 * 果名称
	 */
	private String fruitDesignation;
	/**
	 * 果寓意
	 */
	private String fruitParaphrase;
	/**
	 * 果图片链接
	 */
	private String fruitLink;
	/**
	 * 果小图链接
	 */
	private String fruitSmallLink;
	/**
	 * 果创建时间
	 */
	private Date fruitCreateTime;
	/**
	 * 果过期时间
	 */
	private Date fruitExpireTime;
	/**
	 * 花id
	 */
	private Integer flowersId;
	/**
	 * 花名称
	 */
	private String flowersDesignation;
	/**
	 * 花寓意
	 */
	private String flowersParaphrase;
	/**
	 * 花图片链接
	 */
	private String flowersLink;
	/**
	 * 花小图链接
	 */
	private String flowersSmallLink;
	/**
	 * 花创建时间
	 */
	private Date flowersCreateTime;
	/**
	 * 花过期时间
	 */
	private Date flowersExpireTime;
	/**
	 * 符文列表
	 */
	private List<RuneWithBegInfo> runes;

	public Integer getJossId() {
		if (jossId == null) {
			return 0;
		}
		return jossId;
	}

	public void setJossId(Integer jossId) {
		this.jossId = jossId;
	}

	public Date getJossCreateTime() {
		return jossCreateTime;
	}

	public void setJossCreateTime(Date jossCreateTime) {
		this.jossCreateTime = jossCreateTime;
	}

	public String getJossBuddhaName() {
		if (jossBuddhaName == null) {
			return "";
		}
		return jossBuddhaName;
	}

	public void setJossBuddhaName(String jossBuddhaName) {
		this.jossBuddhaName = jossBuddhaName;
	}

	public String getJossLink() {
		if (jossLink == null) {
			return "";
		}
		return jossLink;
	}

	public void setJossLink(String jossLink) {
		this.jossLink = jossLink;
	}

	public String getJossIntroduction() {
		if (jossIntroduction == null) {
			return "";
		}
		return jossIntroduction;
	}

	public void setJossIntroduction(String jossIntroduction) {
		this.jossIntroduction = jossIntroduction;
	}

	public Integer getSandalwoodId() {
		if (sandalwoodId == null) {
			return 0;
		}
		return sandalwoodId;
	}

	public void setSandalwoodId(Integer sandalwoodId) {
		this.sandalwoodId = sandalwoodId;
	}

	public String getSandalwoodDesignation() {
		if (sandalwoodDesignation == null) {
			return "";
		}
		return sandalwoodDesignation;
	}

	public void setSandalwoodDesignation(String sandalwoodDesignation) {
		this.sandalwoodDesignation = sandalwoodDesignation;
	}

	public String getSandalwoodParaphrase() {
		if (sandalwoodParaphrase == null) {
			return "";
		}
		return sandalwoodParaphrase;
	}

	public void setSandalwoodParaphrase(String sandalwoodParaphrase) {
		this.sandalwoodParaphrase = sandalwoodParaphrase;
	}

	public String getSandalwoodLink() {
		if (sandalwoodLink == null) {
			return "";
		}
		return sandalwoodLink;
	}

	public void setSandalwoodLink(String sandalwoodLink) {
		this.sandalwoodLink = sandalwoodLink;
	}

	public String getSandalwoodSmallLink() {
		if (sandalwoodSmallLink == null) {
			return "";
		}
		return sandalwoodSmallLink;
	}

	public void setSandalwoodSmallLink(String sandalwoodSmallLink) {
		this.sandalwoodSmallLink = sandalwoodSmallLink;
	}

	public Date getSandalwoodCreateTime() {
		return sandalwoodCreateTime;
	}

	public void setSandalwoodCreateTime(Date sandalwoodCreateTime) {
		this.sandalwoodCreateTime = sandalwoodCreateTime;
	}

	public Date getSandalwoodExpireTime() {
		return sandalwoodExpireTime;
	}

	public void setSandalwoodExpireTime(Date sandalwoodExpireTime) {
		this.sandalwoodExpireTime = sandalwoodExpireTime;
	}

	public Integer getFruitId() {
		if (fruitId == null) {
			return 0;
		}
		return fruitId;
	}

	public void setFruitId(Integer fruitId) {
		this.fruitId = fruitId;
	}

	public String getFruitDesignation() {
		if (fruitDesignation == null) {
			return "";
		}
		return fruitDesignation;
	}

	public void setFruitDesignation(String fruitDesignation) {
		this.fruitDesignation = fruitDesignation;
	}

	public String getFruitParaphrase() {
		if (fruitParaphrase == null) {
			return "";
		}
		return fruitParaphrase;
	}

	public void setFruitParaphrase(String fruitParaphrase) {
		this.fruitParaphrase = fruitParaphrase;
	}

	public String getFruitLink() {
		if (fruitLink == null) {
			return "";
		}
		return fruitLink;
	}

	public void setFruitLink(String fruitLink) {
		this.fruitLink = fruitLink;
	}

	public String getFruitSmallLink() {
		if (fruitSmallLink == null) {
			return "";
		}
		return fruitSmallLink;
	}

	public void setFruitSmallLink(String fruitSmallLink) {
		this.fruitSmallLink = fruitSmallLink;
	}

	public Date getFruitCreateTime() {
		return fruitCreateTime;
	}

	public void setFruitCreateTime(Date fruitCreateTime) {
		this.fruitCreateTime = fruitCreateTime;
	}

	public Date getFruitExpireTime() {
		return fruitExpireTime;
	}

	public void setFruitExpireTime(Date fruitExpireTime) {
		this.fruitExpireTime = fruitExpireTime;
	}

	public Integer getFlowersId() {
		if (flowersId == null) {
			return 0;
		}
		return flowersId;
	}

	public void setFlowersId(Integer flowersId) {
		this.flowersId = flowersId;
	}

	public String getFlowersDesignation() {
		if (flowersDesignation == null) {
			return "";
		}
		return flowersDesignation;
	}

	public void setFlowersDesignation(String flowersDesignation) {
		this.flowersDesignation = flowersDesignation;
	}

	public String getFlowersParaphrase() {
		if (flowersParaphrase == null) {
			return "";
		}
		return flowersParaphrase;
	}

	public void setFlowersParaphrase(String flowersParaphrase) {
		this.flowersParaphrase = flowersParaphrase;
	}

	public String getFlowersLink() {
		if (flowersLink == null) {
			return "";
		}
		return flowersLink;
	}

	public void setFlowersLink(String flowersLink) {
		this.flowersLink = flowersLink;
	}

	public String getFlowersSmallLink() {
		if (flowersSmallLink == null) {
			return "";
		}
		return flowersSmallLink;
	}

	public void setFlowersSmallLink(String flowersSmallLink) {
		this.flowersSmallLink = flowersSmallLink;
	}

	public Date getFlowersCreateTime() {
		return flowersCreateTime;
	}

	public void setFlowersCreateTime(Date flowersCreateTime) {
		this.flowersCreateTime = flowersCreateTime;
	}

	public Date getFlowersExpireTime() {
		return flowersExpireTime;
	}

	public void setFlowersExpireTime(Date flowersExpireTime) {
		this.flowersExpireTime = flowersExpireTime;
	}

	public List<RuneWithBegInfo> getRunes() {
		return runes;
	}

	public void setRunes(List<RuneWithBegInfo> runes) {
		this.runes = runes;
	}

}
