package com.wangbei.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wangbei.service.storage.FileSystemStorageService;

@RestController
@RequestMapping("/upload")
public class FileUploadController {

	public static ThreadLocal<MultipartFile> upfileLocal = new ThreadLocal<>();
	
	@Autowired
	private FileSystemStorageService storageService;

	@PostMapping("/")
	public String handleFileUpload(@RequestParam("file") MultipartFile file) {
		storageService.store(file);
		return "You successfully uploaded " + file.getOriginalFilename() + "!";
	}
	
	@RequestMapping(value = "/uploadAttachment", method = { RequestMethod.GET, RequestMethod.POST })
	public void uploadAttachment(@RequestParam(value = "upfile", required = false) MultipartFile file, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
		upfileLocal.set(file);
		storageService.uploadAttachment(servletRequest, servletResponse);
	}

}
