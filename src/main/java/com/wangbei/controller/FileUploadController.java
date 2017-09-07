package com.wangbei.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wangbei.service.storage.FileSystemStorageService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/upload")
@Api(description = "文件上传接口列表")
public class FileUploadController {
	public static ThreadLocal<MultipartFile> upfileLocal = new ThreadLocal<>();

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private FileSystemStorageService storageService;

	@Value("${custom.outer.file}")
	private String fileResource;

	@PostMapping("/")
	public String handleFileUpload(@RequestParam("file") MultipartFile file) {
		storageService.store(file);
		return "You successfully uploaded " + file.getOriginalFilename() + "!";
	}

	@RequestMapping(value = "/uploadAttachment", method = { RequestMethod.GET, RequestMethod.POST })
	public void uploadAttachment(@RequestParam(value = "upfile", required = false) MultipartFile multipart,
			HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
		upfileLocal.set(multipart);
		storageService.uploadAttachment(servletRequest, servletResponse);

	}

	@PostMapping("/file")
	public String fileUpload(@RequestParam("file") MultipartFile file, String fileName) {
		storageService.store(file, fileResource);
		return "You successfully uploaded " + file.getOriginalFilename() + "!";
	}

}
