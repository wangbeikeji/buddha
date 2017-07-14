package com.wangbei.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.wangbei.awre.converter.BannerTypeEnumConverter;
import com.wangbei.util.enums.BannerTypeEnum;

/**
 * banner图
 * 
 * @author luomengan
 *
 */
@Entity
@Table(name = "banner")
public class Banner implements Serializable {

	private static final long serialVersionUID = -4266191976340631932L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * 标题
	 */
	@Column(name = "title", length = 100)
	private String title;

	@Column(name = "type")
	@Convert(converter = BannerTypeEnumConverter.class)
	private BannerTypeEnum type;

	/**
	 * 图片链接
	 */
	@Column(name = "link", length = 255)
	private String link;

	/**
	 * 内容链接
	 */
	@Column(name = "content_link", length = 255)
	private String contentLink;

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

	public BannerTypeEnum getType() {
		return type;
	}

	public void setType(BannerTypeEnum type) {
		this.type = type;
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

}
