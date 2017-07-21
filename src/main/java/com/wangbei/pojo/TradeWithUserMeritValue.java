package com.wangbei.pojo;

import com.wangbei.entity.Trade;

public class TradeWithUserMeritValue extends Trade {

	private static final long serialVersionUID = 1L;

	private Integer userMeritValue;

	public TradeWithUserMeritValue(Trade trade) {
		this.setCreateTime(trade.getCreateTime());
		this.setId(trade.getId());
		this.setMeritValue(trade.getMeritValue());
		this.setStatus(trade.getStatus());
		this.setTradeNo(trade.getTradeNo());
		this.setType(trade.getType());
		this.setUserId(trade.getUserId());
	}

	public Integer getUserMeritValue() {
		return userMeritValue;
	}

	public void setUserMeritValue(Integer userMeritValue) {
		this.userMeritValue = userMeritValue;
	}

}
