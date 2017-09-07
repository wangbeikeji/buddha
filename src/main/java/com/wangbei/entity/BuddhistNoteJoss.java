package com.wangbei.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 佛录佛像
 * 
 * @author luomengan
 *
 */
@Entity
@Table(name = "buddhist_note_joss")
public class BuddhistNoteJoss {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "buddha_name")
	private String buddhaName;
	@Column(name = "link")
	private String link;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBuddhaName() {
		return buddhaName;
	}

	public void setBuddhaName(String buddhaName) {
		this.buddhaName = buddhaName;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

}
