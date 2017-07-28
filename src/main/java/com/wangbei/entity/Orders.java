package com.wangbei.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.wangbei.awre.converter.OrderStatusEnumConverter;
import com.wangbei.awre.converter.PaymentTypeEnumConverter;
import com.wangbei.util.enums.OrderStatusEnum;
import com.wangbei.util.enums.PaymentTypeEnum;

/**
 * @author yuyidi 2017-07-28 14:25:41
 * @class com.wangbei.entity.Orders
 * @description 订单
 */
@Table(name = "orders")
@Entity
public class Orders implements Serializable {

	private static final long serialVersionUID = 3255418001263739242L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "user_id")
	private Integer userId;
	/**
	 * 订单号
	 */
	@Column(name = "order_no", unique = true)
	private String orderNo;
	/**
	 * 交易号
	 */
	@Column(name = "trade_no", unique = true)
	private String tradeNo;
	/**
	 * 第三方交易订单号
	 */
	@Column(name = "thrid_order_no")
	private String thiridOrderNo;
	/**
	 * 订单状态
	 */
	@Column(name = "status")
	@Convert(converter = OrderStatusEnumConverter.class)
	private OrderStatusEnum status;
	/**
	 * 总金额
	 */
	@Column(name = "total_amount")
	private Double totalAmount;
	/**
	 * 支付方式
	 */
	@Column(name = "payment_type")
	@Convert(converter = PaymentTypeEnumConverter.class)
	private PaymentTypeEnum paymentType;
	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;
	/**
	 * 更新时间
	 */
	@Column(name = "modify_time")
	private Date modifyTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getThiridOrderNo() {
		return thiridOrderNo;
	}

	public void setThiridOrderNo(String thiridOrderNo) {
		this.thiridOrderNo = thiridOrderNo;
	}

	public OrderStatusEnum getStatus() {
		return status;
	}

	public void setStatus(OrderStatusEnum status) {
		this.status = status;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public PaymentTypeEnum getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentTypeEnum paymentType) {
		this.paymentType = paymentType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

}
