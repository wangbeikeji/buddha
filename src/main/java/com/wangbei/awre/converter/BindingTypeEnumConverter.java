package com.wangbei.awre.converter;

import com.wangbei.util.enums.BindingTypeEnum;

import javax.persistence.AttributeConverter;

/**
 * 用户绑定类型转换器
 * 
 * @author luomengan
 *
 */
public class BindingTypeEnumConverter implements AttributeConverter<BindingTypeEnum, Integer> {

	@Override
	public Integer convertToDatabaseColumn(BindingTypeEnum attribute) {
		return attribute.getIndex();
	}

	@Override
	public BindingTypeEnum convertToEntityAttribute(Integer dbData) {
		return BindingTypeEnum.getByIndex(dbData);
	}
}
