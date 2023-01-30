package com.botapeer;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class BotapeerApplicationTests {

	@Test
	void contextLoads(ApplicationContext context) {
		assertThat(context).isNotNull();
	}

}
