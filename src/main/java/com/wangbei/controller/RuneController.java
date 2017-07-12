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

import com.wangbei.entity.Rune;
import com.wangbei.pojo.Response;
import com.wangbei.service.RuneService;

/**
 * 符文 Controller
 * 
 * @author luomengan
 *
 */
@RestController
@RequestMapping("/rune")
public class RuneController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public RuneService runeService;

	@GetMapping("/{id}")
	public Response<Rune> fetchById(@PathVariable Integer id) {
		return new Response<>(runeService.getRuneInfo(id));
	}

	@PostMapping("/")
	public Response<Rune> addition(Rune rune) {
		return new Response<>(runeService.addRune(rune));
	}

	@PutMapping("/")
	public Response<Rune> modification(Rune rune) {
		return new Response<>(runeService.modifyRune(rune));
	}

	@DeleteMapping("/")
	public Response<Integer> delete(@PathVariable Integer id) {
		runeService.deleteRune(id);
		return new Response<Integer>(id);
	}

	@GetMapping("/page")
	public Response<Page<Rune>> runes(int page, int limit) {
		return new Response<>((Page<Rune>) runeService.runes(page, limit));
	}

}
