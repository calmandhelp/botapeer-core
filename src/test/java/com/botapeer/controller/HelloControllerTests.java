package com.botapeer.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.botapeer.controller.HelloController;

@WebMvcTest(HelloController.class)
class HelloControllerTests {

	@Autowired
	private MockMvc mockmvc;

	@Test
	@WithMockUser
	void helloTest() throws Exception {
		this.mockmvc.perform(get("/hello")).andDo(print())
				.andExpect(status().isOk());
	}

}
