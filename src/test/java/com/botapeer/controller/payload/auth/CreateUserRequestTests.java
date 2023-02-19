package com.botapeer.controller.payload.auth;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.validation.Validator;

import model.CreateUserRequest;

@SpringBootTest
class CreateUserRequestTests {

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

	//	@Test
	//	public void PasswordEmptyVallidation() {
	//		CreateUserRequest createUserRequest = new CreateUserRequest("test", "test@test.com", "");
	//		BindingResult result = new BindException(createUserRequest, "createUserRequest");
	//
	//		validator.validate(createUserRequest, result);
	//		assertThat(result.getErrorCount()).isEqualTo(1);
	//		assertThat(messageSource.getMessage(result.getFieldError(), Locale.getDefault()))
	//				.isEqualTo("パスワードの空白は許可されていません。");
	//	}
	//
	//	@Test
	//	void EmailEmptyVallidation() {
	//		CreateUserRequest createUserRequest = new CreateUserRequest("test", "", "password");
	//		BindingResult result = new BindException(createUserRequest, "createUserRequest");
	//
	//		validator.validate(createUserRequest, result);
	//		assertThat(result.getErrorCount()).isEqualTo(1);
	//
	//		assertThat(messageSource.getMessage(result.getFieldError(), Locale.getDefault()))
	//				.isEqualTo("メールアドレスの空白は許可されていません。");
	//	}
	//
	//	@Test
	//	void EmailFormatVallidation() {
	//		CreateUserRequest createUserRequest = new CreateUserRequest("test", "aaaa", "password");
	//		BindingResult result = new BindException(createUserRequest, "createUserRequest");
	//
	//		validator.validate(createUserRequest, result);
	//		assertThat(result.getErrorCount()).isEqualTo(1);
	//
	//		assertThat(messageSource.getMessage(result.getFieldError(), Locale.getDefault()))
	//				.isEqualTo("メールアドレスはE-mail形式で入力してください。");
	//	}
	//
	//	@Test
	//	void NameNullVallidation() {
	//		CreateUserRequest createUserRequest = new CreateUserRequest(null, "test@test.com", "password");
	//		BindingResult result = new BindException(createUserRequest, "createUserRequest");
	//
	//		validator.validate(createUserRequest, result);
	//		assertThat(result.getErrorCount()).isEqualTo(1);
	//
	//		assertThat(messageSource.getMessage(result.getFieldError(), Locale.getDefault()))
	//				.isEqualTo("名前の空白は許可されていません。");
	//	}

}
