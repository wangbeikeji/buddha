package com.wangbei.pojo;

import java.util.Map;
import java.util.TreeMap;

public class StaOrderInfo {

	private Map<String, Integer> flowerMap = new TreeMap<>();

	private Map<String, Integer> fruitMap = new TreeMap<>();

	private Map<String, Integer> sandalwoodMap = new TreeMap<>();

	private Map<String, Integer> divinationMap = new TreeMap<>();

	private Map<String, Integer> runeMap = new TreeMap<>();

	private Integer flowerTotalMeritValue = 0;

	private Integer fruitTotalMeritValue = 0;

	private Integer sandalwoodTotalMeritValue = 0;

	private Integer divinationTotalMeritValue = 0;

	private Integer runeTotalMeritValue = 0;

	public Map<String, Integer> getFlowerMap() {
		return flowerMap;
	}

	public void setFlowerMap(Map<String, Integer> flowerMap) {
		this.flowerMap = flowerMap;
	}

	public Map<String, Integer> getFruitMap() {
		return fruitMap;
	}

	public void setFruitMap(Map<String, Integer> fruitMap) {
		this.fruitMap = fruitMap;
	}

	public Map<String, Integer> getSandalwoodMap() {
		return sandalwoodMap;
	}

	public void setSandalwoodMap(Map<String, Integer> sandalwoodMap) {
		this.sandalwoodMap = sandalwoodMap;
	}

	public Map<String, Integer> getDivinationMap() {
		return divinationMap;
	}

	public void setDivinationMap(Map<String, Integer> divinationMap) {
		this.divinationMap = divinationMap;
	}

	public Map<String, Integer> getRuneMap() {
		return runeMap;
	}

	public void setRuneMap(Map<String, Integer> runeMap) {
		this.runeMap = runeMap;
	}

	public Integer getFlowerTotalMeritValue() {
		return flowerTotalMeritValue;
	}

	public void setFlowerTotalMeritValue(Integer flowerTotalMeritValue) {
		this.flowerTotalMeritValue = flowerTotalMeritValue;
	}

	public Integer getFruitTotalMeritValue() {
		return fruitTotalMeritValue;
	}

	public void setFruitTotalMeritValue(Integer fruitTotalMeritValue) {
		this.fruitTotalMeritValue = fruitTotalMeritValue;
	}

	public Integer getSandalwoodTotalMeritValue() {
		return sandalwoodTotalMeritValue;
	}

	public void setSandalwoodTotalMeritValue(Integer sandalwoodTotalMeritValue) {
		this.sandalwoodTotalMeritValue = sandalwoodTotalMeritValue;
	}

	public Integer getDivinationTotalMeritValue() {
		return divinationTotalMeritValue;
	}

	public void setDivinationTotalMeritValue(Integer divinationTotalMeritValue) {
		this.divinationTotalMeritValue = divinationTotalMeritValue;
	}

	public Integer getRuneTotalMeritValue() {
		return runeTotalMeritValue;
	}

	public void setRuneTotalMeritValue(Integer runeTotalMeritValue) {
		this.runeTotalMeritValue = runeTotalMeritValue;
	}

}
