package com.wangbei.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author yuyidi 2017-07-10 21:35:18
 * @class com.wangbei.entity.Joss
 * @description 佛信息
 */
@Entity
@Table(name = "joss")
public class Joss implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "buddha_name")
    private String buddhaName;
    @Column(name = "link")
    private String link;
    @Column(name = "introduction")
    private String introduction;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBuddhaName() {
        return buddhaName;
    }

    public void setBuddhaName(String buddhaName) {
        this.buddhaName = buddhaName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
