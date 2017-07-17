package com.wangbei.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
* @author yuyidi 2017-07-17 15:17:55
* @class com.wangbei.entity.Account
* @description 用户账户功德
*/
@Table(name = "account")
@Entity
public class Account implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "merit_value")
    private Integer meritValue;

    public Account() {
    }

    public Account(Integer userId, Integer meritValue) {
        this.userId = userId;
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

    public Integer getMeritValue() {
        return meritValue;
    }

    public void setMeritValue(Integer meritValue) {
        this.meritValue = meritValue;
    }
}
