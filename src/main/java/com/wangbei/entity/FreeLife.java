package com.wangbei.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
* @author yuyidi 2017-07-18 14:27:45
* @class com.wangbei.entity.FreeLife
* @description 放生池
*/
@Entity
@Table(name = "free_life")
public class FreeLife implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "creature_id")
    private Integer creatureId;
    @Column(name = "merit_value")
    private Integer meritValue;
    @Column(name = "create_time")
    private Date createTime = new Date();


    public FreeLife() {
    }

    public FreeLife(Integer userId, Integer creatureId, Integer meritValue) {
        this.userId = userId;
        this.creatureId = creatureId;
        this.meritValue = meritValue;
    }

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

    public Integer getCreatureId() {
        return creatureId;
    }

    public void setCreatureId(Integer creatureId) {
        this.creatureId = creatureId;
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
