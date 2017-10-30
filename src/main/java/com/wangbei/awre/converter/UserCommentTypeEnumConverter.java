package com.wangbei.awre.converter;

import javax.persistence.AttributeConverter;

import com.wangbei.util.enums.UserCommentTypeEnum;

/**
 * 用户评论类型枚举转换器
 * 
 * @author luomengan
 *
 */
public class UserCommentTypeEnumConverter implements AttributeConverter<UserCommentTypeEnum, Integer> {

	@Override
	public Integer convertToDatabaseColumn(UserCommentTypeEnum attribute) {
		return attribute.getIndex();
	}

	@Override
	public UserCommentTypeEnum convertToEntityAttribute(Integer dbData) {
		return UserCommentTypeEnum.getByIndex(dbData);
	}
}
