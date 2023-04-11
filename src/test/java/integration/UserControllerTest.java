package integration;

import com.botapeer.BotapeerApplication;
import com.botapeer.controller.UserController;
import model.UserResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = BotapeerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Tag("integration-test")
public class UserControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void getUsersOrGetUserByNameTest() throws Exception {
        ResponseEntity<List<UserResponse>> responseEmptyName = restTemplate.exchange(
                "/api/users?username={username}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<UserResponse>>() {
                },
                ""
        );
        assertThat(HttpStatus.OK).isEqualTo(responseEmptyName.getStatusCode());
        assertThat(new ArrayList<>()).isEqualTo(responseEmptyName.getBody());

        ResponseEntity<List<UserResponse>> responseTaro = restTemplate.exchange(
                "/api/users?username={username}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<UserResponse>>() {
                },
                "taro"
        );
        assertThat(HttpStatus.OK).isEqualTo(responseTaro.getStatusCode());
        assertThat("taro").isEqualTo(responseTaro.getBody().get(0).getName());
    }
}
