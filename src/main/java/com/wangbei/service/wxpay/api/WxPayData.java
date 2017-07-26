package com.wangbei.service.wxpay.api;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.NavigableSet;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.wangbei.util.JacksonUtil;
import com.wangbei.util.Md5Util;

public class WxPayData {

	private final Log logger = LogFactory.getLog(getClass());

	private TreeMap<String, Object> dataValues = new TreeMap<String, Object>();

	public void addValue(String key, Object value) {
		dataValues.put(key, value);
	}

	public Object getValue(String key) {
		return dataValues.get(key);
	}

	public boolean isContain(String key) {
		return dataValues.containsKey(key);
	}

	public String toXml() {
		if (dataValues.keySet().size() == 0) {
			logger.error("WxPayData's dataValues is empty!");
			throw new RuntimeException("WxPayData's dataValues is empty!");
		}

		String result = "<xml>";
		NavigableSet<String> keySet = dataValues.navigableKeySet();
		for (String key : keySet) {

			if ("sign".equals(key)) {
				continue;
			}

			Object value = dataValues.get(key);
			if (value instanceof Integer) {
				result += "<" + key + ">" + value + "</" + key + ">";
			} else if (value instanceof String) {
				result += "<" + key + ">" + "<![CDATA[" + value + "]]></" + key + ">";
			} else {
				logger.error("WxPayData's field format is wrong!" + key);
				throw new RuntimeException("WxPayData's field format is wrong!" + key);
			}
		}

		result += "<sign>" + "<![CDATA[" + dataValues.get("sign") + "]]></sign>";

		result += "</xml>";
		return result;
	}

	public TreeMap<String, Object> fromXml(String xml, String appKey) {
		if (xml == null || "".equals(xml)) {
			logger.error("WxPayData's transfer xml is empty!");
			throw new RuntimeException("WxPayData's transfer xml is empty!");
		}

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(new ByteArrayInputStream(xml.getBytes()));
			Element ele = document.getDocumentElement();
			NodeList nodeList = ele.getChildNodes();
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					dataValues.put(node.getNodeName(), node.getTextContent());
				}
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			logger.error("WxPayData parse xml failed!");
			throw new RuntimeException("WxPayData parse xml failed!");
		}

		try {
			if (!"SUCCESS".equals(dataValues.get("return_code"))) {
				return dataValues;
			}
			// 验证签名,不通过会抛异常
			checkSign(appKey);
		} catch (Exception ex) {
			throw new RuntimeException("签名验证不通过!");
		}

		return dataValues;
	}

	public TreeMap<String, Object> fromXml(String xml) {
		if (xml == null || "".equals(xml)) {
			logger.error("WxPayData's transfer xml is empty!");
			throw new RuntimeException("WxPayData's transfer xml is empty!");
		}

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(new ByteArrayInputStream(xml.getBytes()));
			Element ele = document.getDocumentElement();
			NodeList nodeList = ele.getChildNodes();
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					dataValues.put(node.getNodeName(), node.getTextContent());
				}
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			logger.error("WxPayData parse xml failed!");
			throw new RuntimeException("WxPayData parse xml failed!");
		}

		return dataValues;
	}

	public String toUrl() {
		StringBuilder strBuilder = new StringBuilder();

		NavigableSet<String> keySet = dataValues.navigableKeySet();
		for (String key : keySet) {
			Object value = dataValues.get(key);
			if (value == null) {
				logger.error("WxPayData's map value contain null!");
				throw new RuntimeException("WxPayData's map value contain null!");
			}

			if (!"sign".equals(key) && ! (value == null || "".equals(value.toString()))) {
				strBuilder.append(key + "=" + value + "&");
			}
		}
		if (keySet.size() > 0) {
			strBuilder.delete(strBuilder.length() - 1, strBuilder.length());
		}
		return strBuilder.toString();
	}

	public String toJson() {
		return JacksonUtil.encode(dataValues);
	}

	public String toPrintStr() {
		StringBuilder strBuilder = new StringBuilder();
		NavigableSet<String> keySet = dataValues.navigableKeySet();
		for (String key : keySet) {
			Object value = dataValues.get(key);
			if (value == null) {
				logger.error("WxPayData's map value contain null!");
				throw new RuntimeException("WxPayData's map value contain null!");
			}

			if (!"sign".equals(key) && value != null && !"".equals(value.toString())) {
				strBuilder.append(String.format("{0}={1}<br/>", key, value));
			}
		}
		return strBuilder.toString();
	}

	public String makeSign(String appKey) {
		try {
			StringBuilder strBuilder = new StringBuilder(toUrl());
			strBuilder.append("&key=" + appKey);
			return Md5Util.md5(strBuilder.toString()).toUpperCase();
		} catch(Exception ex) {
			throw new RuntimeException("md5摘要失败!", ex);
		}
	}

	public boolean checkSign(String appKey) {
		if (!isContain("sign")) {
			logger.error("WxPayData's sign is not exist!");
			throw new RuntimeException("WxPayData's sign is not exist!");
		} else if (getValue("sign") == null || "".equals(getValue("sign"))) {
			logger.error("WxPayData's sign is invalid!");
			throw new RuntimeException("WxPayData's sign is invalid!");
		}

		String sign = getValue("sign").toString();
		String calSign = makeSign(appKey);

		if (sign.equals(calSign)) {
			return true;
		}
		logger.error("WxPayData's sign is wrong!");
		throw new RuntimeException("WxPayData's sign is wrong!");
	}

	public TreeMap<String, Object> getDataValues() {
		return dataValues;
	}

	public static void main(String[] args) {
		WxPayData data = new WxPayData();
		TreeMap<String, Object> map = data.fromXml("<xml><name><![CDATA[xxx]]></name><age>28</age></xml>",
				"gdyouzhengsudiwuliucaiwu12345678");
		System.out.println(map);

		System.out.println(data.toJson());

		StringBuilder strBuilder = new StringBuilder("luomengan&");
		strBuilder.delete(strBuilder.length() - 1, strBuilder.length());
		System.out.println(strBuilder);
	}
}
