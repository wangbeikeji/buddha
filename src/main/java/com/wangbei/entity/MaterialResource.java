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

import com.wangbei.awre.converter.FileTypeEnumConverter;
import com.wangbei.awre.converter.MaterialResourceTypeEnumConverter;
import com.wangbei.util.enums.FileTypeEnum;
import com.wangbei.util.enums.MaterialResourceTypeEnum;

/**
 * 素材资源
 * 
 * @author luomengan
 *
 */
@Entity
@Table(name = "material_resource")
public class MaterialResource implements Serializable {

	private static final long serialVersionUID = 1543599853996458665L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/**
	 * 素材名称
	 */
	@Column(name = "name")
	private String name;
	/**
	 * 素材链接
	 */
	@Column(name = "link")
	private String link;
	/**
	 * 素材缩略图链接
	 */
	@Column(name = "small_link")
	private String smallLink;
	/**
	 * 素材类型
	 */
	@Column(name = "type")
	@Convert(converter = MaterialResourceTypeEnumConverter.class)
	private MaterialResourceTypeEnum type;
	/**
	 * 文件类型
	 */
	@Column(name = "file_type")
	@Convert(converter = FileTypeEnumConverter.class)
	private FileTypeEnum fileType;
	/**
	 * 是否需要级联更新
	 */
	@Transient
	private boolean isCascadeUpdate = false;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public MaterialResourceTypeEnum getType() {
		return type;
	}

	public void setType(MaterialResourceTypeEnum type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FileTypeEnum getFileType() {
		return fileType;
	}

	public void setFileType(FileTypeEnum fileType) {
		this.fileType = fileType;
	}

	public boolean getIsCascadeUpdate() {
		return isCascadeUpdate;
	}

	public void setIsCascadeUpdate(boolean isCascadeUpdate) {
		this.isCascadeUpdate = isCascadeUpdate;
	}

}
