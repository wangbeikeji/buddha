package com.wangbei.dao.impl.jpa;

import com.wangbei.entity.Orders;
import com.wangbei.util.enums.OrderStatusEnum;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Created by yuyidi on 2017/7/28.
 * @desc
 */
public interface OrdersRepository extends CurdJpaRepository<Orders, Integer>, Repository<Orders, Integer> {

	@Modifying
	@Query("update Orders o set o.status = ?3,o.modifyTime=?4,o.thirdOrderNo=?2 where o.orderNo=?1")
	Integer updateStatusAndTime(String orderNo, String thridOrderNo, OrderStatusEnum orderStatusEnum, Date modifyTime);

	Orders findByTradeNo(String tradeNo);

	Orders findByOrderNo(String orderNo);

	@Query("select round(sum(totalAmount),2) from Orders where modifyTime>=?1 and modifyTime<?2 and status=?3")
	BigDecimal staAmountByDateStage(Date startDate, Date endDate, OrderStatusEnum status);

}
