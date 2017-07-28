package com.wangbei.service;

import com.wangbei.dao.OrderDao;
import com.wangbei.entity.Orders;
import com.wangbei.util.enums.OrderStatusEnum;
import com.wangbei.util.enums.PaymentTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

/**
 * @author Created by yuyidi on 2017/7/28.
 * @desc
 */
@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    @Transactional
    public Orders generateOrder(Integer user, PaymentTypeEnum type, Double totalAmount) {
        Orders order = new Orders();
        order.setUserId(user);
//        order.setOrderNo(RandomUtil.generateNonceStr());
        order.setOrderNo("123456789");
        order.setStatus(OrderStatusEnum.UNPAY);
        order.setPaymentType(type);
        order.setCreateTime(new Date());
        order.setTotalAmount(totalAmount);
        return orderDao.create(order);
    }

    @Transactional
    public Integer updateOrderStatus(String orderNo, String thridOrderNo, OrderStatusEnum orderStatusEnum, Date
            paymentTime) {
        return orderDao.updateOrderStatusAndModifyTime(orderNo, thridOrderNo, orderStatusEnum, paymentTime);
    }
}
