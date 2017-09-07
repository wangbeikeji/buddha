package com.wangbei.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.wangbei.awre.converter.SutraTypeEnumConverter;
import com.wangbei.util.enums.SutraTypeEnum;

/**
 * 经书
 * 
 * @author luomengan
 *
 */
@Entity
@Table(name = "sutra")
public class Sutra implements Serializable {

	private static final long serialVersionUID = 8742568026995969382L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * 标题
	 */
	@Column(name = "title", length = 100)
	private String title;

	/**
	 * 摘要
	 */
	@Type(type = "text")
	@Column(name = "summary")
	private String summary;

	/**
	 * 图片链接
	 */
	@Column(name = "link", length = 255)
	private String link;

	/**
	 * 类型
	 */
	@Column(name = "type")
	@Convert(converter = SutraTypeEnumConverter.class)
	private SutraTypeEnum type;

	/**
	 * 经书内容链接
	 */
	@Column(name = "content_link", length = 255)
	private String contentLink;

	/**
	 * 采集的数据源链接
	 */
	@Column(name = "collect_source_link", length = 255)
	private String collectSourceLink;

	/**
	 * 是否可用
	 */
	@Column(name = "is_enable")
	private Boolean isEnable;
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

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getContentLink() {
		return contentLink;
	}

	public void setContentLink(String contentLink) {
		this.contentLink = contentLink;
	}

	public String getCollectSourceLink() {
		return collectSourceLink;
	}

	public void setCollectSourceLink(String collectSourceLink) {
		this.collectSourceLink = collectSourceLink;
	}

	public Boolean getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}

	public boolean isFavourite() {
		return isFavourite;
	}

	public void setFavourite(boolean isFavourite) {
		this.isFavourite = isFavourite;
	}

	public SutraTypeEnum getType() {
		return type;
	}

	public void setType(SutraTypeEnum type) {
		this.type = type;
	}

}
