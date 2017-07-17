package com.wangbei.entity;

import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author yuyidi 2017-07-14 10:58:42
 * @class com.wangbei.entity.Hereby
 * @description 求符
 */
@Entity
@Table(name = "beg")
public class Beg implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "rune_id")
    private Integer runeId;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "expire_time")
    private Date expireTime;

    public Beg() {
    }

    public Beg(Integer userId, Integer divinationId) {
        this.userId = userId;
        this.runeId = divinationId;
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

    public Integer getRuneId() {
        return runeId;
    }

    public void setRuneId(Integer runeId) {
        this.runeId = runeId;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void expire() {
        DateTime time = new DateTime();
        setCreateTime(time.toDate());
        DateTime expire = time.plusHours(24);
        Date expireTime = expire.toDate();
        setExpireTime(expireTime);
    }


}
