package com.wangbei.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wangbei.service.storage.FileSystemStorageService;

@RestController
@RequestMapping("/upload")
public class FileUploadController {

	@Autowired
	private FileSystemStorageService storageService;

	@PostMapping("/")
	public String handleFileUpload(@RequestParam("file") MultipartFile file) {
		storageService.store(file);
		return "You successfully uploaded " + file.getOriginalFilename() + "!";
	}

}
