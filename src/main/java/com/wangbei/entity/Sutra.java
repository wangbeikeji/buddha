package com.wangbei.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

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
	 * 经书内容链接
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

}
