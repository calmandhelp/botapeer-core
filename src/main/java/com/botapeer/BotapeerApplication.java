package com.botapeer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication(exclude = { UserDetailsServiceAutoConfiguration.class })
public class BotapeerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BotapeerApplication.class, args);
	}
}
