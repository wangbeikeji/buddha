package com.wangbei.pojo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.wangbei.entity.Joss;
import com.wangbei.entity.Offerings;
import com.wangbei.entity.Rune;

/**
 * @author yuyidi 2017-07-14 16:30:54
 * @class com.wangbei.pojo.JossSurrounding
 * @description 佛相关周边信息 eg:供品，求符
 */
@SuppressWarnings("serial")
public class JossSurrounding implements Serializable {

	private Joss joss;
	private Map<String, Offerings> offerings;
	private List<Rune> runes;

	public Joss getJoss() {
		return joss;
	}

	public void setJoss(Joss joss) {
		this.joss = joss;
	}

	public Map<String, Offerings> getOfferings() {
		return offerings;
	}

	public void setOfferings(Map<String, Offerings> offerings) {
		this.offerings = offerings;
	}

	public List<Rune> getRunes() {
		return runes;
	}

	public void setRunes(List<Rune> runes) {
		this.runes = runes;
	}
}
