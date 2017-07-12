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

import com.wangbei.entity.Divination;
import com.wangbei.pojo.Response;
import com.wangbei.service.DivinationService;

/**
 * 灵签 Controller
 * 
 * @author luomengan
 *
 */
@RestController
@RequestMapping("/divination")
public class DivinationController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public DivinationService divinationService;

	@GetMapping("/{id}")
	public Response<Divination> fetchById(@PathVariable Integer id) {
		return new Response<>(divinationService.getDivinationInfo(id));
	}

	@PostMapping("/")
	public Response<Divination> addition(Divination divination) {
		return new Response<>(divinationService.addDivination(divination));
	}

	@PutMapping("/")
	public Response<Divination> modification(Divination divination) {
		return new Response<>(divinationService.modifyDivination(divination));
	}

	@DeleteMapping("/")
	public Response<Integer> delete(@PathVariable Integer id) {
		divinationService.deleteDivination(id);
		return new Response<Integer>(id);
	}

	@GetMapping("/page")
	public Response<Page<Divination>> divinations(int page, int limit) {
		return new Response<>((Page<Divination>) divinationService.divinations(page, limit));
	}

}
