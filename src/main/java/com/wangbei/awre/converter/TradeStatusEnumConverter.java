package com.wangbei.awre.converter;

import javax.persistence.AttributeConverter;

import com.wangbei.util.enums.TradeStatusEnum;

/**
 * 交易状态转换器
 * 
 * @author luomengan
 *
 */
public class TradeStatusEnumConverter implements AttributeConverter<TradeStatusEnum, Integer> {

	@Override
	public Integer convertToDatabaseColumn(TradeStatusEnum attribute) {
		return attribute.getIndex();
	}

	@Override
	public TradeStatusEnum convertToEntityAttribute(Integer dbData) {
		return TradeStatusEnum.getByIndex(dbData);
	}
}
