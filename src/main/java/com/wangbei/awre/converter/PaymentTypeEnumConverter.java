package com.wangbei.awre.converter;

import javax.persistence.AttributeConverter;

import com.wangbei.util.enums.PaymentTypeEnum;

/**
 * 支付类型转换器
 * 
 * @author luomengan
 *
 */
public class PaymentTypeEnumConverter implements AttributeConverter<PaymentTypeEnum, Integer> {

	@Override
	public Integer convertToDatabaseColumn(PaymentTypeEnum attribute) {
		return attribute.getIndex();
	}

	@Override
	public PaymentTypeEnum convertToEntityAttribute(Integer dbData) {
		return PaymentTypeEnum.getByIndex(dbData);
	}
}
