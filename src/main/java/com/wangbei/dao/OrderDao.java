package com.wangbei.dao;

import com.wangbei.entity.Orders;
import com.wangbei.util.enums.OrderStatusEnum;

import java.util.Date;

/**
 * @author Created by yuyidi on 2017/7/28.
 * @desc
 */
public interface OrderDao extends BaseDao<Orders, Integer> {

    Integer updateOrderStatusAndModifyTime(String orderNo, String thridOrderNo, OrderStatusEnum orderStatusEnum, Date
            modifyTime);
}
