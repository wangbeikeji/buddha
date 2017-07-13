package com.wangbei.entity;

import com.wangbei.awre.converter.KnowledgeTypeEnumConverter;
import com.wangbei.util.enums.KnowledgeTypeEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
* @author yuyidi 2017-07-11 19:22:34
* @class com.wangbei.entity.Knowledge
* @description 佛学知识
*/
@Entity
@Table(name = "knowledge")
public class Knowledge implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "title")
    private String title;
    @Column(name = "type")
    @Convert(converter = KnowledgeTypeEnumConverter.class)
    private KnowledgeTypeEnum type;
    @Column(name = "link")
    private String link;
    @Column(name = "context")
    private String context;
    @Column(name = "create_time")
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public KnowledgeTypeEnum getType() {
        return type;
    }

    public void setType(KnowledgeTypeEnum type) {
        this.type = type;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
