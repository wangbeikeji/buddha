package com.wangbei.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wangbei.entity.Joss;
import com.wangbei.pojo.Response;
import com.wangbei.service.JossService;

/**
 * 佛信息 Controller
 * 
 * @author luomengan
 *
 */
@RestController
@RequestMapping("/joss")
public class JossController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public JossService jossService;

	@GetMapping("/{id}")
	public Response<Joss> fetchById(@PathVariable Integer id) {
		return new Response<>(jossService.getJossInfo(id));
	}

	@PostMapping("/")
	public Response<Joss> addition(Joss joss) {
		return new Response<>(jossService.addJoss(joss));
	}

	@PutMapping("/")
	public Response<Joss> modification(Joss joss) {
		return new Response<>(jossService.modifyJoss(joss));
	}

	@DeleteMapping("/")
	public Response<Integer> delete(@PathVariable Integer id) {
		jossService.deleteJoss(id);
		return new Response<Integer>(id);
	}

	@GetMapping("/page")
	public Response<Page<Joss>> josss(int page, int limit) {
		return new Response<>((Page<Joss>) jossService.josss(page, limit));
	}

}
