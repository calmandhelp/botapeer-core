package com.botapeer.controller;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import com.botapeer.controller.payload.auth.CreateUserRequest;
import com.botapeer.usecase.interfaces.IUserUsecase;

@SpringBootTest
class UserControllerTests {

	private IUserUsecase userUsecase;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	Validator validator;

	private CreateUserRequest createUserRequestMock = mock(CreateUserRequest.class);

	@BeforeEach
	void setUp() {
		when(createUserRequestMock.getEmail()).thenReturn("test@test.com");
		when(createUserRequestMock.getName()).thenReturn("test");
		when(createUserRequestMock.getPassword()).thenReturn("password");
	}

	@Test
	public void Email() {
		assertEquals("test@test.com", createUserRequestMock.getEmail());
	}

	@Test
	public void Name() {
		assertEquals("test", createUserRequestMock.getName());
	}

	@Test
	public void Password() {
		assertEquals("password", createUserRequestMock.getPassword());
	}

	@Test
	public void PasswordEmptyVallidation() {
		CreateUserRequest createUserRequest = new CreateUserRequest("test", "test@test.com", "");
		BindingResult bindingResult = new BindException(createUserRequest, "createUserRequest");

		validator.validate(createUserRequest, bindingResult);
		assertThat(bindingResult.getErrorCount()).isEqualTo(1);
		assertThat(messageSource.getMessage(bindingResult.getFieldError(), Locale.getDefault()))
				.isEqualTo("passwordの空白は許可されていません。");
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
