package com.wangbei.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
* @author yuyidi 2017-07-10 21:41:42
* @class com.wangbei.entity.offerings
* @description 供品
*/
// @Entity
// @Table(name = "offerings")
public class offerings implements Serializable{

    private Integer id;
    private String name;
}
