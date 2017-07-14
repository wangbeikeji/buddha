package com.wangbei.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
* @author yuyidi 2017-07-14 10:58:42
* @class com.wangbei.entity.Hereby
* @description 恭请佛
*/
@Entity
@Table(name = "hereby")
public class Hereby implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "user_phone")
    private String userPhone;
    @Column(name = "joss_id")
    private Integer jossId;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "expire_ime")
    private Date expireTime;

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

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Integer getJossId() {
        return jossId;
    }

    public void setJossId(Integer jossId) {
        this.jossId = jossId;
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
}
