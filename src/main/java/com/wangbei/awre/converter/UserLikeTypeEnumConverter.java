package com.wangbei.awre.converter;

import javax.persistence.AttributeConverter;

import com.wangbei.util.enums.UserLikeTypeEnum;

/**
 * 用户点赞类型枚举转换器
 * 
 * @author luomengan
 *
 */
public class UserLikeTypeEnumConverter implements AttributeConverter<UserLikeTypeEnum, Integer> {

	@Override
	public Integer convertToDatabaseColumn(UserLikeTypeEnum attribute) {
		return attribute.getIndex();
	}

	@Override
	public UserLikeTypeEnum convertToEntityAttribute(Integer dbData) {
		return UserLikeTypeEnum.getByIndex(dbData);
	}
}
