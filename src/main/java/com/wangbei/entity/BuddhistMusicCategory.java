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

import org.hibernate.annotations.Type;

/**
 * 佛音类别
 * 
 * @author luomengan
 *
 */
@Entity
@Table(name = "buddhist_music_category")
public class BuddhistMusicCategory implements Serializable {

	private static final long serialVersionUID = 4801662664552277792L;

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
	 * 作者
	 */
	@Column(name = "author")
	private String author;

	/**
	 * 描述
	 */
	@Column(name = "txt_description")
	@Type(type = "text")
	private String txtDescription;

	/**
	 * 层级
	 */
	@Column(name = "level")
	private Integer level;

	/**
	 * 是否首页顶部推荐
	 */
	@Column(name = "is_home_top")
	private Boolean isHomeTop;

	/**
	 * 首页顶部推荐排序
	 */
	@Column(name = "home_top_sort_num")
	private Integer homeTopSortNum;

	/**
	 * 是否推荐
	 */
	@Column(name = "is_recommend")
	private Boolean isRecommend = false;

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
	 * 封面链接
	 */
	@Column(name = "cover_link4", length = 350)
	private String coverLink4;

	/**
	 * 封面链接
	 */
	@Column(name = "cover_link5", length = 350)
	private String coverLink5;

	/**
	 * 首页顶部推荐banner图
	 */
	@Column(name = "banner_link")
	private String bannerLink;

	/**
	 * 排序
	 */
	@Column(name = "sort_num")
	private Integer sortNum;

	/**
	 * 父级类别
	 */
	@ManyToOne
	@JoinColumn(name = "parent_id")
	private BuddhistMusicCategory parent;

	/**
	 * 播放次数
	 */
	@Column(name = "play_times")
	private Integer playTimes;

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

	public BuddhistMusicCategory getParent() {
		return parent;
	}

	public void setParent(BuddhistMusicCategory parent) {
		this.parent = parent;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Boolean getIsHomeTop() {
		return isHomeTop;
	}

	public void setIsHomeTop(Boolean isHomeTop) {
		this.isHomeTop = isHomeTop;
	}

	public Integer getHomeTopSortNum() {
		return homeTopSortNum;
	}

	public void setHomeTopSortNum(Integer homeTopSortNum) {
		this.homeTopSortNum = homeTopSortNum;
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

	public String getCoverLink4() {
		return coverLink4;
	}

	public void setCoverLink4(String coverLink4) {
		this.coverLink4 = coverLink4;
	}

	public String getCoverLink5() {
		return coverLink5;
	}

	public void setCoverLink5(String coverLink5) {
		this.coverLink5 = coverLink5;
	}

	public String getBannerLink() {
		return bannerLink;
	}

	public void setBannerLink(String bannerLink) {
		this.bannerLink = bannerLink;
	}

}
