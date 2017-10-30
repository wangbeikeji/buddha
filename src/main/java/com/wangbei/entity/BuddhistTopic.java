package com.wangbei.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.wangbei.awre.converter.BuddhistTopicStatusEnumConverter;
import com.wangbei.util.enums.BuddhistTopicStatusEnum;

/**
 * 修行主题
 * 
 * @author luomengan
 *
 */
@Entity
@Table(name = "buddhist_topic")
public class BuddhistTopic {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/**
	 * 标题
	 */
	@Column(name = "title")
	private String title;
	/**
	 * 主题详情
	 */
	@Column(name = "topic_detail")
	private String topicDetail;
	/**
	 * 图片链接
	 */
	@Column(name = "link")
	private String link;
	/**
	 * 缩略图链接
	 */
	@Column(name = "small_link")
	private String smallLink;
	/**
	 * 素材id
	 */
	@Column(name = "material_resource_id")
	private Integer materialResourceId;
	/**
	 * 开始时间
	 */
	@Column(name = "start_time")
	private Date startTime;
	/**
	 * 结束时间
	 */
	@Column(name = "end_time")
	private Date endTime;
	/**
	 * 主题时间，例如主题为地震同胞祈祷平安，则主题时间为地震发生日期
	 */
	@Column(name = "topic_time")
	private Date topicTime;
	/**
	 * 状态
	 */
	@Column(name = "status")
	@Convert(converter = BuddhistTopicStatusEnumConverter.class)
	private BuddhistTopicStatusEnum status;
	/**
	 * 排序
	 */
	@Column(name = "sort_num")
	private Integer sortNum;
	/**
	 * 是否启用
	 */
	@Column(name = "is_enable")
	private Boolean isEnable;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTopicDetail() {
		return topicDetail;
	}

	public void setTopicDetail(String topicDetail) {
		this.topicDetail = topicDetail;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getTopicTime() {
		return topicTime;
	}

	public void setTopicTime(Date topicTime) {
		this.topicTime = topicTime;
	}

	public BuddhistTopicStatusEnum getStatus() {
		return status;
	}

	public void setStatus(BuddhistTopicStatusEnum status) {
		this.status = status;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getSmallLink() {
		return smallLink;
	}

	public void setSmallLink(String smallLink) {
		this.smallLink = smallLink;
	}

	public Boolean getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}

	public Integer getMaterialResourceId() {
		return materialResourceId;
	}

	public void setMaterialResourceId(Integer materialResourceId) {
		this.materialResourceId = materialResourceId;
	}

	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

}
