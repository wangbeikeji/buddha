package com.wangbei.entity;

import com.wangbei.awre.converter.GenderEnumConverter;
import com.wangbei.util.enums.GenderEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author yuyidi 2017-07-06 16:52:01
 * @class com.wangbei.entity.User
 * @description 用户
 */
@Entity
@Table(name = "user")
public class  User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "phone", unique = true, nullable = false)
    private String phone;
    @Column(name = "password", nullable = false)
    private String password;
    @Convert(converter = GenderEnumConverter.class)
    @Column(name = "gender")
    private GenderEnum gender;
    @Column(name = "birthday")
    private Date birthday;
    @Column(name = "signature")
    private String signature;
    @Column(name = "create_time")
    private Date creatTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }
}
