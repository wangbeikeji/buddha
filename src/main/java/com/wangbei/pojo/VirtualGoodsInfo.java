package com.wangbei.pojo;

import java.io.Serializable;

/**
* @author yuyidi 2017-07-28 15:10:48
* @class com.wangbei.pojo.VirtualGoodsInfo
* @description 虚拟的支付商品信息
*/
@SuppressWarnings("serial")
public class VirtualGoodsInfo implements Serializable{
    //订单标题
    private String subject;
    //商品描述
    private String body;

    public VirtualGoodsInfo(String subject, String body) {
        this.subject = subject;
        this.body = body;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
