package com.wangbei.util.enums;

import com.wangbei.util.enums.CommonalityEnum;

/**
 * @author Created by yuyidi on 2017/6/27.
 * @desc 短信供应商类型
 */
public enum SmsTypeEnum implements CommonalityEnum {
	Ali(0, "阿里"), Sxt(1, "风信通"), Lexin(3, "乐信"), Other(4, "其他");

	SmsTypeEnum(Integer index, String type) {
		this.index = index;
		this.type = type;
	}

	private Integer index;
	private String type;

	@Override
	public Integer getIndex() {
		return this.index;
	}

	public String getType() {
		return type;
	}
}
