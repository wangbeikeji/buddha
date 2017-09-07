package com.wangbei.awre.converter;

import javax.persistence.AttributeConverter;

import com.wangbei.util.enums.SutraTypeEnum;

/**
 * 经书类型转换器
 * 
 * @author luomengan
 *
 */
public class SutraTypeEnumConverter implements AttributeConverter<SutraTypeEnum, Integer> {

	@Override
	public Integer convertToDatabaseColumn(SutraTypeEnum attribute) {
		return attribute.getIndex();
	}

	@Override
	public SutraTypeEnum convertToEntityAttribute(Integer dbData) {
		return SutraTypeEnum.getByIndex(dbData);
	}

}
