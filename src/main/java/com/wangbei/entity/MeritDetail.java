package com.wangbei.entity;

import com.wangbei.awre.converter.OfferingTypeEnumConverter;
import com.wangbei.util.enums.OfferingTypeEnum;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
* @author yuyidi 2017-07-14 17:32:11
* @class com.wangbei.entity.MeritDetail
* @description 功德详情
*/
@Entity
@Table(name = "merit_detail")
public class MeritDetail implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_id",nullable = false)
    private Integer userId;
    @Column(name = "offerings_id",nullable = false)
    private Integer offeringsId;
    @Column(name = "merit_value")
    private Integer meritValue;
    @Convert(converter = OfferingTypeEnumConverter.class)
    @Column(name = "type")
    private OfferingTypeEnum type;
    @Column(name = "create_time")
    private Date    createTime;
    @Column(name = "expire_time")
    private Date    expireTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOfferingsId() {
        return offeringsId;
    }

    public void setOfferingsId(Integer offeringsId) {
        this.offeringsId = offeringsId;
    }

    public Integer getMeritValue() {
        return meritValue;
    }

    public void setMeritValue(Integer meritValue) {
        this.meritValue = meritValue;
    }

    public OfferingTypeEnum getType() {
        return type;
    }

    public void setType(OfferingTypeEnum type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public MeritDetail() {
    }

    public MeritDetail(Integer userId, Integer offeringsId, Integer meritValue, OfferingTypeEnum type) {
        this.userId = userId;
        this.offeringsId = offeringsId;
        this.meritValue = meritValue;
        this.type = type;
    }

    /**
     * 设置过期时间
     */
    public void expire() {
        DateTime time = new DateTime();
        setCreateTime(time.toDate());
        DateTime expire = time.plusHours(24);
        Date expireTime = expire.toDate();
        setExpireTime(expireTime);
    }


}
