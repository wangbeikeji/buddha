package com.wangbei.pojo;

import com.wangbei.util.enums.DivinationTypeEnum;

/**
 * 用户摇签信息
 * 
 * @author luomengan
 *
 */
public class UserShakeDivinationInfo {

	/**
	 * 用户求签记录id
	 */
	private Integer id;

	/**
	 * 用户id
	 */
	private Integer userId;

	/**
	 * 签诗类型
	 */
	private DivinationTypeEnum type;

	/**
	 * 签诗
	 */
	private String poem;

	/**
	 * 解签需要消耗的功德数
	 */
	private int meritValue;

	public UserShakeDivinationInfo() {
		super();
	}

	public UserShakeDivinationInfo(Integer id, Integer userId, DivinationTypeEnum type, String poem, int meritValue) {
		super();
		this.id = id;
		this.userId = userId;
		this.type = type;
		this.poem = poem;
		this.meritValue = meritValue;
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

	public DivinationTypeEnum getType() {
		return type;
	}

	public void setType(DivinationTypeEnum type) {
		this.type = type;
	}

	public String getPoem() {
		return poem;
	}

	public void setPoem(String poem) {
		this.poem = poem;
	}

	public int getMeritValue() {
		return meritValue;
	}

	public void setMeritValue(int meritValue) {
		this.meritValue = meritValue;
	}

}
