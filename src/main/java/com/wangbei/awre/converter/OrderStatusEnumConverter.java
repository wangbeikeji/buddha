package com.wangbei.awre.converter;

import com.wangbei.util.enums.OrderStatusEnum;
import com.wangbei.util.enums.TradeStatusEnum;

import javax.persistence.AttributeConverter;

/**
 * 交易状态转换器
 * 
 * @author luomengan
 *
 */
public class OrderStatusEnumConverter implements AttributeConverter<OrderStatusEnum, Integer> {

	@Override
	public Integer convertToDatabaseColumn(OrderStatusEnum attribute) {
		return attribute.getIndex();
	}

	@Override
	public OrderStatusEnum convertToEntityAttribute(Integer dbData) {
		return OrderStatusEnum.getByIndex(dbData);
	}
}
