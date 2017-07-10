package com.wangbei.entity;

import com.wangbei.awre.converter.DivinationTypeEnumConverter;
import com.wangbei.util.enums.DivinationTypeEnum;

import javax.persistence.*;
import java.io.Serializable;

/**
* @author yuyidi 2017-07-10 21:27:38
* @class com.wangbei.entity.Divination
* @description 灵签
*/
@Entity
@Table(name = "divination")
public class Divination implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "sequence_number")
    private String sequenceNumber;
    @Column(name = "poem")
    private String poem;
    @Column(name = "type")
    @Convert(converter = DivinationTypeEnumConverter.class)
    private DivinationTypeEnum type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(String sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public String getPoem() {
        return poem;
    }

    public void setPoem(String poem) {
        this.poem = poem;
    }

    public DivinationTypeEnum getType() {
        return type;
    }

    public void setType(DivinationTypeEnum type) {
        this.type = type;
    }
}
