package com.ryokujun.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	//	@Bean
	//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	//		http.csrf().disable();
	//
	//		http.authorizeRequests()
	//				.antMatchers("/login").permitAll()
	//				.anyRequest().authenticated();
	//		return http.build();
	//	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.formLogin(login -> login
				.loginProcessingUrl("/login")
				.loginPage("/login")
				.defaultSuccessUrl("/")
				.failureUrl("/login?error")
				.permitAll()).logout(logout -> logout
						.logoutSuccessUrl("/"))
				.authorizeHttpRequests(authz -> authz
						.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
						//						.mvcMatchers("/").permitAll()
						//						.mvcMatchers("/general").hasRole("GENERAL")
						//						.mvcMatchers("/admin").hasRole("ADMIN")
						.anyRequest().authenticated());
		return http.build();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
