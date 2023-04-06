package com.botapeer.controller;

import api.UsersApi;
import com.botapeer.domain.model.user.Password;
import com.botapeer.domain.model.user.User;
import com.botapeer.domain.model.user.UserName;
import com.botapeer.exception.NotFoundException;
import com.botapeer.usecase.interfaces.IUserUsecase;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

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

        Mockito.when(userUsecase.findUsers("")).thenReturn(userList);
        Mockito.when(userUsecase.findUsers(null)).thenReturn(userList);

            for(UserResponse mockUserInUserList: userList) {
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

}
