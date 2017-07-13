package com.wangbei.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * 灵签 Test
 * 
 * @author luomengan
 *
 */
public class DivinationControllerTest extends BaseControllerTest {

	@Test
	public void fetchById() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/divination/{id}", 1).accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andExpect(jsonPath("$.result.id").value("1"));
	}

	@Test
	public void addition() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/divination/")
				.param("sequenceNumber", "0")
				.param("summarize", "此卦心平正直之象，凡事安稳无凶也。此签家宅安，自身平，求财守慎，交易稳，婚姻合，六甲男，行人有信，田蚕稳，六畜安，寻人迟见，讼和，移徙守旧，失物见，病禳星，山坟平。")
				.param("type", "2")
				.param("reference", "金星试窦儿")
				.param("poem", "一条金线秤君心 无减无增无重轻 为人平生心正直 文章全具艺光明")
				.param("solution", "心平正直 到底清平 只依本份 天下太平")
				.param("sketch", "此卦心平正直之象，凡事安稳无凶也。")
				.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());
	}

	@Test
	public void modification() throws Exception {
		mvc.perform(MockMvcRequestBuilders.put("/divination/")
				.param("id", "2")
				.param("summarize", "aaa_modification")
				.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.result.id").value(2))
				.andExpect(jsonPath("$.result.summarize").value("aaa_modification"));
	}
	
	@Test
	public void delete() throws Exception {
		mvc.perform(MockMvcRequestBuilders.delete("/divination/{id}", 3)
				.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.result").value(3));
	}
	
	@Test
	public void divinations() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/divination/page")
				.param("page", "0")
				.param("limit", "10")
				.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.result.size").isNumber());
	}
	
}