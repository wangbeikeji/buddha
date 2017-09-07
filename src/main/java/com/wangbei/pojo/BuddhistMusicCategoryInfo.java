package com.wangbei.pojo;

import javax.persistence.Column;

import com.wangbei.entity.BuddhistMusicCategory;

/**
 * 佛音类别信息
 * 
 * @author luomengan
 *
 */
public class BuddhistMusicCategoryInfo {

	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 作者
	 */
	private String author;
	/**
	 * 描述
	 */
	private String txtDescription;
	/**
	 * 层级
	 */
	private Integer level;
	/**
	 * 封面链接
	 */
	@Column(name = "cover_link1", length = 350)
	private String coverLink1;

	/**
	 * 封面链接
	 */
	@Column(name = "cover_link2", length = 350)
	private String coverLink2;

	/**
	 * 封面链接
	 */
	@Column(name = "cover_link3", length = 350)
	private String coverLink3;
	/**
	 * 是否推荐
	 */
	private Boolean isRecommend;
	/**
	 * 排序
	 */
	private Integer sortNum;
	/**
	 * 父级类别id
	 */
	private Integer parentId;
	/**
	 * 父级类别名称
	 */
	private String parentName;
	/**
	 * 播放次数
	 */
	private Integer playTimes = 0;

	public BuddhistMusicCategoryInfo() {
		super();
	}

	public BuddhistMusicCategoryInfo(BuddhistMusicCategory entity) {
		this.setTxtDescription(entity.getTxtDescription());
		this.setId(entity.getId());
		this.setIsRecommend(entity.getIsRecommend());
		this.setLevel(entity.getLevel());
		this.setName(entity.getName());
		this.setSortNum(entity.getSortNum());
		this.setCoverLink1(entity.getCoverLink1());
		this.setCoverLink2(entity.getCoverLink2());
		this.setCoverLink3(entity.getCoverLink3());
		this.setPlayTimes(entity.getPlayTimes());
		this.setAuthor(entity.getAuthor());
		if (entity.getParent() != null) {
			this.setParentId(entity.getParent().getId());
			this.setParentName(entity.getParent().getName());
		}
	}

	public BuddhistMusicCategory toBuddhistMusicCategoryEntity() {
		BuddhistMusicCategory entity = new BuddhistMusicCategory();
		entity.setTxtDescription(this.getTxtDescription());
		entity.setId(this.getId());
		entity.setIsRecommend(this.getIsRecommend());
		entity.setName(this.getName());
		entity.setLevel(this.getLevel());
		entity.setSortNum(this.getSortNum());
		entity.setAuthor(this.getAuthor());
		entity.setCoverLink1(this.getCoverLink1());
		entity.setCoverLink2(this.getCoverLink2());
		entity.setCoverLink3(this.getCoverLink3());
		entity.setPlayTimes(this.getPlayTimes());
		return entity;
	}

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

	public String getTxtDescription() {
		return txtDescription;
	}

	public void setTxtDescription(String txtDescription) {
		this.txtDescription = txtDescription;
	}

	public Boolean getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(Boolean isRecommend) {
		this.isRecommend = isRecommend;
	}

	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getCoverLink1() {
		return coverLink1;
	}

	public void setCoverLink1(String coverLink1) {
		this.coverLink1 = coverLink1;
	}

	public String getCoverLink2() {
		return coverLink2;
	}

	public void setCoverLink2(String coverLink2) {
		this.coverLink2 = coverLink2;
	}

	public String getCoverLink3() {
		return coverLink3;
	}

	public void setCoverLink3(String coverLink3) {
		this.coverLink3 = coverLink3;
	}

	public Integer getPlayTimes() {
		return playTimes;
	}

	public void setPlayTimes(Integer playTimes) {
		this.playTimes = playTimes;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

}
