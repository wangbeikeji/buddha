package com.wangbei.awre.converter;

import javax.persistence.AttributeConverter;

import com.wangbei.util.enums.PushTypeEnum;

/**
 * 消息推送类型转换器
 * 
 * @author luomengan
 *
 */
public class PushTypeEnumConverter implements AttributeConverter<PushTypeEnum, Integer> {

	@Override
	public Integer convertToDatabaseColumn(PushTypeEnum attribute) {
		return attribute.getIndex();
	}

	@Override
	public PushTypeEnum convertToEntityAttribute(Integer dbData) {
		return PushTypeEnum.getByIndex(dbData);
	}

}
