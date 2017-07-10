package com.wangbei.service.storage;

import org.springframework.stereotype.Component;

@Component
public class StorageProperties {

	/**
	 * 存储文件的路径
	 */
	private String location = "D:/upload-dir";

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
