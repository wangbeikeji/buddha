package com.wangbei.awre.converter;

import javax.persistence.AttributeConverter;

import com.wangbei.util.enums.OfferingTypeEnum;

/**
* @author yuyidi 2017-07-06 17:24:07
* @class com.wangbei.awre.converter.GenderEnumConverter
* @description 自定义供品类型转换器
*/
public class OfferingTypeEnumConverter implements AttributeConverter<OfferingTypeEnum,Integer> {

    /**
    * @author yuyidi 2017-07-06 17:24:41
    * @method convertToDatabaseColumn
    * @param attribute
    * @return java.lang.Integer
    * @description 将枚举类型转换成数据库字段值
    */
    @Override
    public Integer convertToDatabaseColumn(OfferingTypeEnum attribute) {
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
    public OfferingTypeEnum convertToEntityAttribute(Integer dbData) {
        return OfferingTypeEnum.getByIndex(dbData);
    }
}
