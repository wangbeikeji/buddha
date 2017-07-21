package com.wangbei.ueditor.upload;

import java.util.Map;

import org.springframework.util.Base64Utils;

import com.wangbei.ueditor.PathFormat;
import com.wangbei.ueditor.define.AppInfo;
import com.wangbei.ueditor.define.BaseState;
import com.wangbei.ueditor.define.FileType;
import com.wangbei.ueditor.define.State;

public final class Base64Uploader {

	public static State save(String content, Map<String, Object> conf) {

		byte[] data = decode(content);

		long maxSize = ((Long) conf.get("maxSize")).longValue();

		if (!validSize(data, maxSize)) {
			return new BaseState(false, AppInfo.MAX_SIZE);
		}

		String suffix = FileType.getSuffix("JPG");

		String savePath = PathFormat.parse((String) conf.get("savePath"), (String) conf.get("filename"));

		savePath = savePath + suffix;
		String physicalPath = (String) conf.get("rootPath") + savePath;

		State storageState = StorageManager.saveBinaryFile(data, physicalPath);

		if (storageState.isSuccess()) {
			storageState.putInfo("url", PathFormat.format(savePath));
			storageState.putInfo("type", suffix);
			storageState.putInfo("original", "");
		}

		return storageState;
	}

	private static byte[] decode(String content) {
		return Base64Utils.decodeFromString(content);
	}

	private static boolean validSize(byte[] data, long length) {
		return data.length <= length;
	}

}