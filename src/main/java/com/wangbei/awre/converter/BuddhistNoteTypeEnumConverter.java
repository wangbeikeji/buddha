package com.wangbei.awre.converter;

import javax.persistence.AttributeConverter;

import com.wangbei.util.enums.BuddhistNoteTypeEnum;

/**
 * 佛录类型转换器
 * 
 * @author luomengan
 *
 */
public class BuddhistNoteTypeEnumConverter implements AttributeConverter<BuddhistNoteTypeEnum, Integer> {

	@Override
	public Integer convertToDatabaseColumn(BuddhistNoteTypeEnum attribute) {
		return attribute.getIndex();
	}

	@Override
	public BuddhistNoteTypeEnum convertToEntityAttribute(Integer dbData) {
		return BuddhistNoteTypeEnum.getByIndex(dbData);
	}

}
