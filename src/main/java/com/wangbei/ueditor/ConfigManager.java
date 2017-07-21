package com.wangbei.ueditor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import com.wangbei.ueditor.define.ActionMap;
import com.wangbei.util.JacksonUtil;

/**
 * 配置管理器
 * 
 * @author hancong03@baidu.com
 *
 */
public final class ConfigManager {

	private final String rootPath;
	private final String originalPath;
	@SuppressWarnings("unused")
	private final String contextPath;
	private static final String configFileName = "config.json";
	private String parentPath = null;
	private ConfigBean configBean = null;
	// 涂鸦上传filename定义
	private final static String SCRAWL_FILE_NAME = "scrawl";
	// 远程图片抓取filename定义
	private final static String REMOTE_FILE_NAME = "remote";

	/*
	 * 通过一个给定的路径构建一个配置管理器， 该管理器要求地址路径所在目录下必须存在config.properties文件
	 */
	private ConfigManager(String rootPath, String contextPath, String uri) throws FileNotFoundException, IOException {

		rootPath = rootPath.replace("\\", "/");

		this.rootPath = rootPath;
		this.contextPath = contextPath;

		if (contextPath.length() > 0) {
			this.originalPath = this.rootPath + uri.substring(contextPath.length());
		} else {
			this.originalPath = this.rootPath + uri;
		}

		this.initEnv();

	}

	/**
	 * 配置管理器构造工厂
	 * 
	 * @param rootPath
	 *            服务器根路径
	 * @param contextPath
	 *            服务器所在项目路径
	 * @param uri
	 *            当前访问的uri
	 * @return 配置管理器实例或者null
	 */
	public static ConfigManager getInstance(String rootPath, String contextPath, String uri) {

		try {
			return new ConfigManager(rootPath, contextPath, uri);
		} catch (Exception e) {
			return null;
		}

	}

	// 验证配置文件加载是否正确
	public boolean valid() {
		return this.configBean != null;
	}

	public ConfigBean getAllConfig() {
		return this.configBean;

	}

	public Map<String, Object> getConfig(int type) {

		Map<String, Object> conf = new HashMap<String, Object>();
		String savePath = null;

		switch (type) {

		case ActionMap.UPLOAD_FILE:
			conf.put("isBase64", "false");
			conf.put("maxSize", this.configBean.getFileMaxSize());
			conf.put("allowFiles", this.configBean.getFileAllowFiles());
			conf.put("fieldName", this.configBean.getFileFieldName());
			savePath = this.configBean.getFilePathFormat();
			break;

		case ActionMap.UPLOAD_IMAGE:
			conf.put("isBase64", "false");
			conf.put("maxSize", this.configBean.getImageMaxSize());
			conf.put("allowFiles", this.configBean.getImageAllowFiles());
			conf.put("fieldName", this.configBean.getImageFieldName());
			savePath = this.configBean.getImagePathFormat();
			break;

		case ActionMap.UPLOAD_VIDEO:
			conf.put("maxSize", this.configBean.getVideoMaxSize());
			conf.put("allowFiles", this.configBean.getVideoAllowFiles());
			conf.put("fieldName", this.configBean.getVideoFieldName());
			savePath = this.configBean.getVideoPathFormat();
			break;

		case ActionMap.UPLOAD_SCRAWL:
			conf.put("filename", ConfigManager.SCRAWL_FILE_NAME);
			conf.put("maxSize", this.configBean.getScrawlMaxSize());
			conf.put("fieldName", this.configBean.getScrawlFieldName());
			conf.put("isBase64", "true");
			savePath = this.configBean.getScrawlPathFormat();
			break;

		case ActionMap.CATCH_IMAGE:
			conf.put("filename", ConfigManager.REMOTE_FILE_NAME);
			conf.put("filter", this.configBean.getCatcherLocalDomain());
			conf.put("maxSize", this.configBean.getCatcherMaxSize());
			conf.put("allowFiles", this.configBean.getCatcherAllowFiles());
			conf.put("fieldName", this.configBean.getCatcherFieldName());
			savePath = this.configBean.getCatcherPathFormat();
			break;

		case ActionMap.LIST_IMAGE:
			conf.put("allowFiles", this.configBean.getImageManagerAllowFiles());
			conf.put("dir", this.configBean.getImageManagerListPath());
			conf.put("count", this.configBean.getImageManagerListSize());
			break;

		case ActionMap.LIST_FILE:
			conf.put("allowFiles", this.configBean.getFileManagerAllowFiles());
			conf.put("dir", this.configBean.getFileManagerListPath());
			conf.put("count", this.configBean.getFileManagerListSize());
			break;

		}

		conf.put("savePath", savePath);
		conf.put("rootPath", this.rootPath);

		return conf;

	}

	private void initEnv() throws FileNotFoundException, IOException {

		File file = new File(this.originalPath);

		if (!file.isAbsolute()) {
			file = new File(file.getAbsolutePath());
		}

		this.parentPath = file.getParent();

		String configContent = this.readFile(this.getConfigPath());

		try {
			ConfigBean configBean = JacksonUtil.decode(configContent, ConfigBean.class);
			this.configBean = configBean;
		} catch (Exception e) {
			this.configBean = null;
		}

	}

	private String getConfigPath() {
		return this.parentPath + File.separator + ConfigManager.configFileName;
	}

	private String readFile(String path) throws IOException {

		StringBuilder builder = new StringBuilder();

		try {

			// 将config.json放在classpath中，修改默认的读取方式
			InputStreamReader reader = new InputStreamReader(
					this.getClass().getClassLoader().getResourceAsStream(ConfigManager.configFileName), "UTF-8");
			BufferedReader bfReader = new BufferedReader(reader);

			String tmpContent = null;

			while ((tmpContent = bfReader.readLine()) != null) {
				builder.append(tmpContent);
			}

			bfReader.close();

		} catch (UnsupportedEncodingException e) {
			// 忽略
		}

		return this.filter(builder.toString());

	}

	// 过滤输入字符串, 剔除多行注释以及替换掉反斜杠
	private String filter(String input) {

		return input.replaceAll("/\\*[\\s\\S]*?\\*/", "");

	}

}
