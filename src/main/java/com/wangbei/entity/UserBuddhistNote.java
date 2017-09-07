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

import org.hibernate.annotations.Type;

import com.wangbei.awre.converter.BuddhistNoteTypeEnumConverter;
import com.wangbei.util.enums.BuddhistNoteTypeEnum;

/**
 * 用户佛录
 * 
 * @author luomengan
 *
 */
@Entity
@Table(name = "user_buddhist_note")
public class UserBuddhistNote implements Serializable {

	private static final long serialVersionUID = 2875805695209057384L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * 佛像
	 */
	@Column(name = "buddha")
	private String buddha;

	/**
	 * 佛像图片链接
	 */
	@Column(name = "buddha_link")
	private String buddhaLink;

	/**
	 * 佛录内容
	 */
	@Column(name = "note")
	@Type(type = "text")
	private String note;

	/**
	 * 佛录类型
	 */
	@Column(name = "type")
	@Convert(converter = BuddhistNoteTypeEnumConverter.class)
	private BuddhistNoteTypeEnum type;

	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;

	/**
	 * 用户id
	 */
	@Column(name = "user_id")
	private Integer userId;

	/**
	 * 是否公开
	 */
	@Column(name = "is_public")
	private Boolean isPublic = false;

	/**
	 * 手机号
	 */
	@Column(name = "phone")
	private String phone;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBuddha() {
		return buddha;
	}

	public void setBuddha(String buddha) {
		this.buddha = buddha;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public BuddhistNoteTypeEnum getType() {
		return type;
	}

	public void setType(BuddhistNoteTypeEnum type) {
		this.type = type;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Boolean getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}

	public String getBuddhaLink() {
		return buddhaLink;
	}

	public void setBuddhaLink(String buddhaLink) {
		this.buddhaLink = buddhaLink;
	}

	public String getPhone() {
		if (phone != null) {
			return phone.substring(0, 3) + "****" + phone.substring(7);
		}
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
