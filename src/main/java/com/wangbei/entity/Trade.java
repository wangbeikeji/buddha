package com.wangbei.entity;

import com.wangbei.awre.converter.TradeTypeEnumConverter;
import com.wangbei.util.enums.TradeTypeEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Created by yuyidi on 2017/7/17.
 * @desc
 */
@Table(name = "trade")
@Entity
public class Trade implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "type")
    @Convert(converter = TradeTypeEnumConverter.class)
    private TradeTypeEnum type;
    @Column(name = "merit_value")
    private Integer meritValue;
    @Column(name = "create_time")
    private Date createTime = new Date();

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

    public TradeTypeEnum getType() {
        return type;
    }

    public void setType(TradeTypeEnum type) {
        this.type = type;
    }

    public Integer getMeritValue() {
        return meritValue;
    }

    public void setMeritValue(Integer meritValue) {
        this.meritValue = meritValue;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
