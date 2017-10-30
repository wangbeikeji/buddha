package com.wangbei.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 佛音
 * 
 * @author luomengan
 *
 */
@Entity
@Table(name = "buddhist_music")
public class BuddhistMusic implements Serializable {

	private static final long serialVersionUID = -701416599149587199L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * 歌名
	 */
	@Column(name = "name")
	private String name;

	/**
	 * 作者
	 */
	@Column(name = "author")
	private String author;

	/**
	 * 资源链接
	 */
	@Column(name = "link", length = 350)
	private String link;

	/**
	 * 封面链接（统一使用专辑的图片）
	 */
	@Transient
	private String coverLink1;

	/**
	 * 封面链接（统一使用专辑的图片）
	 */
	@Transient
	private String coverLink2;

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
	 * 首页顶部推荐banner图
	 */
	@Column(name = "banner_link")
	private String bannerLink;

	/**
	 * 是否推荐
	 */
	@Column(name = "is_recommend")
	private Boolean isRecommend = false;

	/**
	 * 排序
	 */
	@Column(name = "sort_num")
	private Integer sortNum;
	/**
	 * 类别id
	 */
	@Column(name = "category_id")
	private Integer categoryId;
	/**
	 * 所属专辑名称
	 */
	@Transient
	private String categoryName;
	/**
	 * 播放次数
	 */
	@Column(name = "play_times")
	private Integer playTimes;

	/**
	 * 音乐时长
	 */
	@Column(name = "duration")
	private Integer duration;

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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Boolean getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(Boolean isRecommend) {
		this.isRecommend = isRecommend;
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

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getBannerLink() {
		return bannerLink;
	}

	public void setBannerLink(String bannerLink) {
		this.bannerLink = bannerLink;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
