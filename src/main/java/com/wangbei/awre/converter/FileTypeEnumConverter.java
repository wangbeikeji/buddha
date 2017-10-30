package com.wangbei.awre.converter;

import javax.persistence.AttributeConverter;

import com.wangbei.util.enums.FileTypeEnum;

/**
 * 文件类型
 * 
 * @author luomengan
 *
 */
public class FileTypeEnumConverter implements AttributeConverter<FileTypeEnum, Integer> {

	@Override
	public Integer convertToDatabaseColumn(FileTypeEnum attribute) {
		return attribute.getIndex();
	}

	@Override
	public FileTypeEnum convertToEntityAttribute(Integer dbData) {
		return FileTypeEnum.getByIndex(dbData);
	}
}
