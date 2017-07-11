package com.wangbei.awre.converter;

import com.wangbei.util.enums.DivinationTypeEnum;

import javax.persistence.AttributeConverter;

/**
* @author yuyidi 2017-07-06 17:24:07
* @class com.wangbei.awre.converter.GenderEnumConverter
* @description 自定义灵签类型转换器
*/
public class DivinationTypeEnumConverter implements AttributeConverter<DivinationTypeEnum,Integer> {

    /**
    * @author yuyidi 2017-07-06 17:24:41
    * @method convertToDatabaseColumn
    * @param attribute
    * @return java.lang.Integer
    * @description 将枚举类型转换成数据库字段值
    */
    @Override
    public Integer convertToDatabaseColumn(DivinationTypeEnum attribute) {
        return  attribute.getIndex();
    }

    /**
    * @author yuyidi 2017-07-06 17:25:09
    * @method convertToEntityAttribute
    * @param dbData
    * @return com.wangbei.util.enums.GenderEnum
    * @description 将数据库字段值转换成枚举
    */
    @Override
    public DivinationTypeEnum convertToEntityAttribute(Integer dbData) {
        return DivinationTypeEnum.getByIndex(dbData);
    }
}
