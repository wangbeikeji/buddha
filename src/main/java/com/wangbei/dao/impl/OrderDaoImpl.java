package com.wangbei.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import com.wangbei.dao.DynamicQuerySqlDao;
import com.wangbei.dao.OrderDao;
import com.wangbei.dao.impl.jpa.OrdersRepository;
import com.wangbei.entity.Orders;
import com.wangbei.util.enums.OrderStatusEnum;

/**
 * @author Created by yuyidi on 2017/7/28.
 * @desc
 */
@Repository
public class OrderDaoImpl implements OrderDao {

	@Autowired
	private OrdersRepository ordersRepository;

	@Autowired
	private DynamicQuerySqlDao dynamicQuerySqlDao;

	@Override
	public Orders create(Orders order) {
		return ordersRepository.save(order);
	}

	@Override
	public void deleteById(Integer id) {

	}

	@Override
	public Orders update(Orders order) {
		return null;
	}

	@Override
	public Orders retrieveById(Integer id) {
		return null;
	}

	@Override
	public Page<Orders> page(int page, int limit) {
		return null;
	}

	@Override
	public List<Orders> list() {
		return null;
	}

	@Override
	public Integer updateOrderStatusAndModifyTime(String orderNo, String thridOrderNo, OrderStatusEnum orderStatusEnum,
			Date modifyTime) {
		return ordersRepository.updateStatusAndTime(orderNo, thridOrderNo, orderStatusEnum, modifyTime);
	}

	@Override
	public Orders getOrderByTradeNo(String tradeNo) {
		return ordersRepository.findByTradeNo(tradeNo);
	}

	@Override
	public Orders fetchOrderByOrderNo(String orderNo) {
		return ordersRepository.findByOrderNo(orderNo);
	}

	@Override
	public Double staAmountByDateStage(Date startDate, Date endDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sql = String.format(
				"select round(sum(o.total_amount),2) from orders o, user u where o.user_id=u.id and (u.is_test is null or u.is_test=0) %s %s and status=2",
				"and o.modify_time>='" + sdf.format(startDate) + "'",
				"and o.modify_time<'" + sdf.format(endDate) + "'");
		return dynamicQuerySqlDao.executeComputeSql(sql);
		// BigDecimal bigD = ordersRepository.staAmountByDateStage(startDate,
		// endDate, OrderStatusEnum.SUCCESS);
	}
}
