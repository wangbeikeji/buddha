package com.wangbei.awre.converter;

import javax.persistence.AttributeConverter;

import com.wangbei.util.enums.BannerTypeEnum;

/**
 * 自定义banner图类型转换器
 * 
 * @author luomengan
 *
 */
public class BannerTypeEnumConverter implements AttributeConverter<BannerTypeEnum, Integer> {

	/**
	 * 将枚举类型转换成数据库字段值
	 */
	@Override
	public Integer convertToDatabaseColumn(BannerTypeEnum attribute) {
		return attribute.getIndex();
	}

	/**
	 * 将数据库字段值转换成枚举
	 */
	@Override
	public BannerTypeEnum convertToEntityAttribute(Integer dbData) {
		return BannerTypeEnum.getByIndex(dbData);
	}
}
