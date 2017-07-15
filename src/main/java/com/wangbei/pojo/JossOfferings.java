package com.wangbei.pojo;

import com.wangbei.entity.Joss;
import com.wangbei.entity.Offerings;
import com.wangbei.util.enums.OfferingTypeEnum;

import java.io.Serializable;
import java.util.Map;

/**
 * @author yuyidi 2017-07-14 16:30:54
 * @class com.wangbei.pojo.JossOfferings
 * @description 佛与供品信息
 */
public class JossOfferings implements Serializable{

    public Joss joss;
    public Map<String,Offerings> offerings;

    public Joss getJoss() {
        return joss;
    }

    public void setJoss(Joss joss) {
        this.joss = joss;
    }

    public Map<String, Offerings> getOfferings() {
        return offerings;
    }

    public void setOfferings(Map<String, Offerings> offerings) {
        this.offerings = offerings;
    }
}
