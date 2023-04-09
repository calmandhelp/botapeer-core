package java.com.botapeer.controller;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class UserControllerTest {

    private MockMvc mockMvc;

    void getUsersOrGetUserByNameTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users").param("username", ""))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));

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
