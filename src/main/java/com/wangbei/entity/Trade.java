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

import com.wangbei.awre.converter.PaymentTypeEnumConverter;
import com.wangbei.awre.converter.TradeStatusEnumConverter;
import com.wangbei.awre.converter.TradeTypeEnumConverter;
import com.wangbei.util.enums.PaymentTypeEnum;
import com.wangbei.util.enums.TradeStatusEnum;
import com.wangbei.util.enums.TradeTypeEnum;

/**
 * @author Created by yuyidi on 2017/7/17.
 * @desc 交易
 */
@Table(name = "trade")
@Entity
public class Trade implements Serializable {

	private static final long serialVersionUID = -4758687958524782093L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/**
	 * 用户id
	 */
	@Column(name = "user_id")
	private Integer userId;
	/**
	 * 交易类型
	 */
	@Column(name = "type")
	@Convert(converter = TradeTypeEnumConverter.class)
	private TradeTypeEnum type;
	/**
	 * 交易流水号
	 */
	@Column(name = "trade_no", unique = true)
	private String tradeNo;
	/**
	 * 第三方交易流水号
	 */
	@Column(name = "third_trade_no")
	private String thirdTradeNo;
	/**
	 * 交易状态
	 */
	@Column(name = "status")
	@Convert(converter = TradeStatusEnumConverter.class)
	private TradeStatusEnum status;
	/**
	 * 功德数
	 */
	@Column(name = "merit_value")
	private Integer meritValue;
	/**
	 * 总金额
	 */
	@Column(name = "total_fee")
	private Double totalFee;
	@Column(name = "payment_type")
	@Convert(converter = PaymentTypeEnumConverter.class)
	private PaymentTypeEnum paymentType;
	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime = new Date();

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

	public TradeTypeEnum getType() {
		return type;
	}

	public void setType(TradeTypeEnum type) {
		this.type = type;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public TradeStatusEnum getStatus() {
		return status;
	}

	public void setStatus(TradeStatusEnum status) {
		this.status = status;
	}

	public Integer getMeritValue() {
		return meritValue;
	}

	public void setMeritValue(Integer meritValue) {
		this.meritValue = meritValue;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Double getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Double totalFee) {
		this.totalFee = totalFee;
	}

	public PaymentTypeEnum getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentTypeEnum paymentType) {
		this.paymentType = paymentType;
	}

	public String getThirdTradeNo() {
		return thirdTradeNo;
	}

	public void setThirdTradeNo(String thirdTradeNo) {
		this.thirdTradeNo = thirdTradeNo;
	}

}
