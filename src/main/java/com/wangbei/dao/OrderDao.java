package com.wangbei.dao;

import java.util.Date;

import com.wangbei.entity.Orders;
import com.wangbei.util.enums.OrderStatusEnum;

/**
 * @author Created by yuyidi on 2017/7/28.
 * @desc
 */
public interface OrderDao extends BaseDao<Orders, Integer> {

    Integer updateOrderStatusAndModifyTime(String orderNo, String thridOrderNo, OrderStatusEnum orderStatusEnum, Date
            modifyTime);
    
    Orders getOrderByTradeNo(String tradeNo);
    
}
