package com.wangbei.controller;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Created by yuyidi on 2017/7/6.
 * @desc
 */
public class UserControllerTest extends BaseControllerTest {

    @Test
    public void addition() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/user/register")
                .param("name", "yuyidi")
                .param("phone", "18008622878")
                .param("password", "123456")
                .param("gender", "1")
                .param("birthday", "199206")
                .param("validateCode","123456")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void login() throws Exception {
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/user/login")
                .param("phone", "18008622878")
                .param("password", "123456")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }

}