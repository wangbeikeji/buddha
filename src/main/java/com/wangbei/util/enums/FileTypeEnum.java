package com.wangbei.util.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

/**
 * 文件类型
 * 
 * @author luomengan
 *
 */
public enum FileTypeEnum implements CommonalityEnum {

	Picture(1, "图片"),

	Attachment(2, "附件");

	private Integer index;
	private String type;

	FileTypeEnum(Integer index, String type) {
		this.index = index;
		this.type = type;
	}

	@Override
	public Integer getIndex() {
		return index;
	}

	@JsonValue
	public String getType() {
		return type;
	}

	private static Map<Integer, FileTypeEnum> valueMap = new HashMap<>();

	static {
		for (FileTypeEnum _enum : FileTypeEnum.values()) {
			valueMap.put(_enum.getIndex(), _enum);
		}
	}

	public static FileTypeEnum getByIndex(Integer index) {
		FileTypeEnum result = valueMap.get(index);
		if (result == null) {
			throw new IllegalArgumentException("No element matches " + index);
		}
		return result;
	}

}
