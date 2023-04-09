package com.botapeer.controller;

import api.UsersApi;
import com.botapeer.domain.model.user.Password;
import com.botapeer.domain.model.user.User;
import com.botapeer.domain.model.user.UserName;
import com.botapeer.exception.NotFoundException;
import com.botapeer.usecase.interfaces.IUserUsecase;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.UpdateUserFormData;
import model.UserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTests {

    private MockMvc mockMvc;

    @Mock
    private IUserUsecase userUsecase;

    @BeforeEach
    void setup() {
        Collection<UserResponse> userList = new ArrayList<>();
        UserResponse user1 = new UserResponse();
        user1.setId(1);
        user1.setName("taro");
        user1.setEmail("taro@taro.com");
        user1.setDescription("説明1");
        user1.setProfileImage("/images/profileImage");
        user1.setCoverImage("/images/coverImage");
        user1.setStatus(1);
        UserResponse user2 = new UserResponse();
        user2.setId(2);
        user2.setName("jiro");
        user2.setEmail("jiro@jiro.com");
        user2.setDescription("説明2");
        user2.setProfileImage("/images/profileImage");
        user2.setCoverImage("/images/coverImage");
        user2.setStatus(1);
        UserResponse user3 = new UserResponse();
        user3.setId(3);
        user3.setName("saburo");
        user3.setEmail("saburo@saburo.com");
        user3.setDescription("説明3");
        user3.setProfileImage("/images/profileImage");
        user3.setCoverImage("/images/coverImage");
        user3.setStatus(1);
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);

        MockitoAnnotations.openMocks(this);

        Mockito.when(userUsecase.findById(Mockito.anyString())).thenReturn(Optional.empty());
        Mockito.when(userUsecase.findUsers("")).thenReturn(userList);
        Mockito.when(userUsecase.findUsers(null)).thenReturn(userList);
        Mockito.when(userUsecase.update(Mockito.isA(UpdateUserFormData.class),
                Mockito.isA(MultipartFile.class),
                Mockito.isA(MultipartFile.class),
                Mockito.anyString())).thenAnswer(invocation -> {
                UpdateUserFormData updateUserFormData = invocation.getArgument(0);
                String updateUserId = invocation.getArgument(3);

                for(UserResponse updateMockUserInUserList: userList) {
                    if(updateUserId.equals(updateMockUserInUserList.getId().toString())) {
                        updateMockUserInUserList.setName(updateUserFormData.getName());
                        updateMockUserInUserList.setEmail(updateUserFormData.getEmail());
                        return Optional.of(updateMockUserInUserList);
                    }
                }
                    return Optional.empty();
                });

            for(UserResponse mockUserInUserList: userList) {
                Mockito.when(userUsecase.findById(mockUserInUserList.getId().toString())).thenReturn(Optional.of(mockUserInUserList));

                Mockito.when(userUsecase.findUsers(mockUserInUserList.getName())).thenAnswer(invocation -> {
                    String userName = invocation.getArgument(0);
                    if(mockUserInUserList.getName().equals(userName)) {
                       Collection<UserResponse> userReponses = new ArrayList<>();
                        userReponses.add(mockUserInUserList);
                        return userReponses;
                    }
                    return Optional.empty();
                });
        };

        Mockito.when(userUsecase.findByPlantRecordId(Mockito.anyString())).thenReturn(Optional.of(new UserResponse()));

        this.mockMvc = MockMvcBuilders.standaloneSetup(new UserController(userUsecase)).build();
    }

    @Test
    void getUsersOrGetUserByNameTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users").param("username", ""))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/users").param("username", "aaa"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/users").param("username", "taro"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("taro")))
                .andExpect(jsonPath("$[0].email", is("taro@taro.com")))
                .andExpect(jsonPath("$[0].description", is("説明1")))
                .andExpect(jsonPath("$[0].profileImage", is("/images/profileImage")))
                .andExpect(jsonPath("$[0].coverImage", is("/images/coverImage")))
                .andExpect(jsonPath("$[0].status", is(1)));
    }

    @Test
    void findUserByIdTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/{userId}", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", is("taro")))
                .andExpect(jsonPath("email", is("taro@taro.com")))
                .andExpect(jsonPath("description", is("説明1")))
                .andExpect(jsonPath("profileImage", is("/images/profileImage")))
                .andExpect(jsonPath("coverImage", is("/images/coverImage")))
                .andExpect(jsonPath("status", is(1)));
    }

    @Test
    void updateUserTest() throws Exception {
        RequestPostProcessor requestPostProcessor = request -> {
            request.setMethod("PATCH");
            return request;
        };

        UpdateUserFormData formData = new UpdateUserFormData("goro", "goro@goro.com");
        MockMultipartFile formDataImage = new MockMultipartFile("formData", "", MediaType.APPLICATION_JSON_VALUE, new ObjectMapper().writeValueAsBytes(formData));
        MockMultipartFile profileImage = new MockMultipartFile("profileImage", new byte[0]);
        MockMultipartFile coverImage = new MockMultipartFile("coverImage", new byte[0]);

        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/users/{userId}", 1)
                .file(formDataImage)
                .file(profileImage)
                .file(coverImage)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.MULTIPART_FORM_DATA_VALUE)
                .with(requestPostProcessor))
                .andExpect(status().isOk());

        UpdateUserFormData formDataTooLongName = new UpdateUserFormData("goroaaafegghwagera", "goro@goro.com");
        MockMultipartFile formDataImageTooLongName = new MockMultipartFile("formData", "", MediaType.APPLICATION_JSON_VALUE, new ObjectMapper().writeValueAsBytes(formDataTooLongName));
        MockMultipartFile profileImageTooLongName = new MockMultipartFile("profileImage", new byte[0]);
        MockMultipartFile coverImageTooLongName = new MockMultipartFile("coverImage", new byte[0]);

        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/users/{userId}", 1)
                        .file(formDataImageTooLongName)
                        .file(profileImageTooLongName)
                        .file(coverImageTooLongName)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.MULTIPART_FORM_DATA_VALUE)
                        .with(requestPostProcessor))
                        .andExpect(status().is4xxClientError());

        UpdateUserFormData formDataInvalidEmailFormat = new UpdateUserFormData("goro", "goro");
        MockMultipartFile formDataImageInvalidEmailFormat = new MockMultipartFile("formData", "", MediaType.APPLICATION_JSON_VALUE, new ObjectMapper().writeValueAsBytes(formDataInvalidEmailFormat));
        MockMultipartFile profileImageInvalidEmailFormat = new MockMultipartFile("profileImage", new byte[0]);
        MockMultipartFile coverImageInvalidEmailFormat = new MockMultipartFile("coverImage", new byte[0]);

        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/users/{userId}", 1)
                        .file(formDataImageInvalidEmailFormat)
                        .file(profileImageInvalidEmailFormat)
                        .file(coverImageInvalidEmailFormat)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.MULTIPART_FORM_DATA_VALUE)
                        .with(requestPostProcessor))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void deleteUserTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/users/{userId}", 1))
                .andExpect(status().isOk());
    }

    @Test
    void findUserByPlantRecordIdTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/plant_records/{plantRecordId}", 1))
                .andExpect(status().isOk());
    }

}
