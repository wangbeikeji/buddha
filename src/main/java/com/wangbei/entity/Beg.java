package com.wangbei.entity;

import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author yuyidi 2017-07-14 10:58:42
 * @class com.wangbei.entity.Hereby
 * @description 求符
 */
@Entity
@Table(name = "beg")
public class Beg implements Serializable {

	private static final long serialVersionUID = 1713969526294578727L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/**
	 * 用户id
	 */
	@Column(name = "user_id")
	private Integer userId;
	/**
	 * 佛id
	 */
	@Column(name = "joss_id")
	private Integer jossId;
	/**
	 * 符id
	 */
	@Column(name = "rune_id")
	private Integer runeId;
	/**
	 * 消耗的功德数
	 */
	@Column(name = "merit_value")
	private Integer meritValue;
	/**
	 * 是否为自己请符
	 * 
	 * <ul>
	 * <li>true为自己请符</li>
	 * <li>false为他人请符</li>
	 * </ul>
	 */
	@Column(name = "is_self")
	private Boolean isSelf = true;
	/**
	 * 他人姓名
	 */
	@Column(name = "other_name")
	private String otherName;
	/**
	 * 他人性别
	 * 
	 * <ul>
	 * <li>0男</li>
	 * <li>1女</li>
	 * </ul>
	 */
	@Column(name = "other_gender")
	private Integer otherGender;
	/**
	 * 他人生日
	 */
	@Column(name = "other_birthday")
	private String otherBirthday;
	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;
	/**
	 * 过期时间
	 */
	@Column(name = "expire_time")
	private Date expireTime;

	public Beg() {
	}

	public Beg(Integer userId, Integer divinationId) {
		this.userId = userId;
		this.runeId = divinationId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getRuneId() {
		return runeId;
	}

	public void setRuneId(Integer runeId) {
		this.runeId = runeId;
	}

	public Integer getJossId() {
		return jossId;
	}

	public void setJossId(Integer jossId) {
		this.jossId = jossId;
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

	public Integer getMeritValue() {
		return meritValue;
	}

	public void setMeritValue(Integer meritValue) {
		this.meritValue = meritValue;
	}

	public void expire() {
		DateTime time = new DateTime();
		setCreateTime(time.toDate());
		DateTime expire = time.plusHours(24);
		Date expireTime = expire.toDate();
		setExpireTime(expireTime);
	}

}
