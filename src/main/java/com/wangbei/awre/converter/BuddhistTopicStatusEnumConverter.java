package com.wangbei.awre.converter;

import javax.persistence.AttributeConverter;

import com.wangbei.util.enums.BuddhistTopicStatusEnum;

/**
 * 修行主题状态枚举转换器
 * 
 * @author luomengan
 *
 */
public class BuddhistTopicStatusEnumConverter implements AttributeConverter<BuddhistTopicStatusEnum, Integer> {

	@Override
	public Integer convertToDatabaseColumn(BuddhistTopicStatusEnum attribute) {
		return attribute.getIndex();
	}

	@Override
	public BuddhistTopicStatusEnum convertToEntityAttribute(Integer dbData) {
		return BuddhistTopicStatusEnum.getByIndex(dbData);
	}
}
