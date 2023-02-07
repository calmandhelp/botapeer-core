package com.botapeer.controller;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTests {

	private final MockMvc mockMvc;
	//	private final UserController userController;

	@BeforeEach
	public void setup() {
		//		this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}

	//	@Test
	//	void getUserController() throws Exception {
	//		mockMvc.perform(get("/api/users"))
	//				.andExpect(status().isOk());
	//	}

	//	@Test
	//	void getUserTest() throws Exception {
	//		this.mockMvc.perform(MockMvcRequestBuilders.get("/users/1"))
	//				.andExpect(status().isOk());
	//	}

}
