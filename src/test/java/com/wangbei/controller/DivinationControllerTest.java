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
				.param("sequenceNumber", "24")
				.param("summarize", "此卦痴人道塞之象，凡事守旧待时也。此签家宅不安，自身险，求财阻，交易难，婚姻待时，田蚕六畜多灾，六甲祈福，行人寻人阻，讼亏，移徙吉，病急急求神，失物难见， 山坟不吉。")
				.param("type", "3")
				.param("reference", "殷效遇师")
				.param("poem", "不成理论不成家 水性痴人似落花 若问君恩须得力 到头方见事如麻")
				.param("solution", "是非莫说 必须仔细 心正理直 方免灾危")
				.param("sketch", "此卦痴人道塞之象，凡事守旧待时也。")
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