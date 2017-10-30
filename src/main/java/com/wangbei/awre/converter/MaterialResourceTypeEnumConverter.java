package com.wangbei.awre.converter;

import javax.persistence.AttributeConverter;

import com.wangbei.util.enums.MaterialResourceTypeEnum;

/**
 * 图片素材类型枚举转换器
 * 
 * @author luomengan
 *
 */
public class MaterialResourceTypeEnumConverter implements AttributeConverter<MaterialResourceTypeEnum, Integer> {

	@Override
	public Integer convertToDatabaseColumn(MaterialResourceTypeEnum attribute) {
		return attribute.getIndex();
	}

	@Override
	public MaterialResourceTypeEnum convertToEntityAttribute(Integer dbData) {
		return MaterialResourceTypeEnum.getByIndex(dbData);
	}
}
