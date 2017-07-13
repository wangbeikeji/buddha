package com.wangbei.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
* @author yuyidi 2017-07-10 21:24:10
* @class com.wangbei.entity.Rune
* @description 符文
*/
@Entity
@Table(name = "rune")
public class Rune implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "symbol")
    private String symbol;
    @Column(name = "introduction")
    private String introduction;
    @Column(name = "link")
    private String link;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
