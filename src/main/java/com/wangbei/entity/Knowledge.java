package com.wangbei.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.wangbei.awre.converter.KnowledgeTypeEnumConverter;
import com.wangbei.util.enums.KnowledgeTypeEnum;

/**
 * @author yuyidi 2017-07-11 19:22:34
 * @class com.wangbei.entity.Knowledge
 * @description 佛学知识
 */
@Entity
@Table(name = "knowledge")
public class Knowledge implements Serializable {

	private static final long serialVersionUID = 200132137993323273L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/**
	 * 标题
	 */
	@Column(name = "title")
	private String title;
	/**
	 * 摘要
	 */
	@Column(name = "summary")
	private String summary;
	/**
	 * 类型
	 */
	@Column(name = "type")
	@Convert(converter = KnowledgeTypeEnumConverter.class)
	private KnowledgeTypeEnum type;
	/**
	 * 缩略图链接
	 */
	@Column(name = "link")
	private String link;
	/**
	 * 内容
	 */
	@Type(type = "text")
	@Column(name = "context")
	private String context;
	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;
	/**
	 * 排序
	 */
	@Column(name = "sort_num")
	private Integer sortNum;
	/**
	 * 是否收藏
	 */
	@Transient
	private boolean isFavourite;

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

	public KnowledgeTypeEnum getType() {
		return type;
	}

	public void setType(KnowledgeTypeEnum type) {
		this.type = type;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

	public boolean isFavourite() {
		return isFavourite;
	}

	public void setFavourite(boolean isFavourite) {
		this.isFavourite = isFavourite;
	}

}
