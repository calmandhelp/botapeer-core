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
        assertThat(responseEmptyName.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEmptyName.getBody().size()).isEqualTo(3);

        ResponseEntity<List<UserResponse>> responseTaro = restTemplate.exchange(
                "/api/users?username={username}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<UserResponse>>() {
                },
                "taro"
        );
        assertThat(responseTaro.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseTaro.getBody().get(0).getName()).isEqualTo("taro");

        ResponseEntity<List<UserResponse>> responseJiro = restTemplate.exchange(
                "/api/users?username={username}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<UserResponse>>() {
                },
                "jiro"
        );
        assertThat(responseJiro.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseJiro.getBody().get(0).getName()).isEqualTo("jiro");

        ResponseEntity<List<UserResponse>> responseTaroEmail = restTemplate.exchange(
                "/api/users?username={username}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<UserResponse>>() {
                },
                "hanako"
        );
        assertThat(responseTaroEmail.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseTaroEmail.getBody().size()).isEqualTo(0);


    }
}
