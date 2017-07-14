package com.wangbei.controller;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Created by yuyidi on 2017/7/14.
 * @desc
 */
public class HerebyControllerTest extends BaseControllerTest{
    @Test
    public void additionHereby() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/hereby/")
                .param("joss", "1")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

}