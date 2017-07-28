package com.wangbei.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangbei.dao.OrderDao;
import com.wangbei.entity.Orders;
import com.wangbei.util.RandomUtil;
import com.wangbei.util.enums.OrderStatusEnum;
import com.wangbei.util.enums.PaymentTypeEnum;

/**
 * @author Created by yuyidi on 2017/7/28.
 * @desc
 */
@Service
public class OrderService {
	
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

    @Autowired
    private OrderDao orderDao;

    @Transactional
    public Orders generateOrder(Integer user, PaymentTypeEnum type, Double totalAmount, String orderNo, String tradeNo) {
        Orders order = new Orders();
        order.setUserId(user);
        order.setOrderNo(orderNo);
        order.setTradeNo(tradeNo);
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
    
    @Transactional
    public Orders completeOrders(String tradeNo, String thiridOrderNo) {
    	Orders orders = orderDao.getOrderByTradeNo(tradeNo);
    	orders.setStatus(OrderStatusEnum.SUCCESS);
    	if(thiridOrderNo != null) {
    		orders.setThirdOrderNo(thiridOrderNo);
    	}
    	orders.setModifyTime(new Date());
    	orderDao.update(orders);
    	return orders;
    }
    
    @Transactional
    public Orders completeOrders(String tradeNo) {
    	return completeOrders(tradeNo, null);
    }
    
    /**
     * 生成交易流水号
     *
     * @return
     */
    public static String generateOrderNo() {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(sdf.format(new Date()));
        strBuilder.append(RandomUtil.generateRandomCode(6));
        return strBuilder.toString();
    }

    public Orders getOrderByOrderNo(String orderNo) {
        return orderDao.fetchOrderByOrderNo(orderNo);
    }
}
