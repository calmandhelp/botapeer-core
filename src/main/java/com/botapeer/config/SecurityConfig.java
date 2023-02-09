package com.botapeer.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.botapeer.security.JwtAuthenticationEntryPoint;
import com.botapeer.security.JwtAuthenticationFilter;
import com.botapeer.security.oauth2.HttpCookieOAuth2AuthorizationRequestRepository;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final JwtAuthenticationEntryPoint unauthorizedHandler;
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	//	private final CustomOAuth2UserService oauthUserService;
	//	private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
	//	private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;
	//	private final LogoutSuccessHandler logoutSuccessHandler;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf().disable()
				.exceptionHandling()
				.authenticationEntryPoint(unauthorizedHandler)
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authorizeHttpRequests(authz -> authz
						.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
						.mvcMatchers("/swagger-ui/**", "/v3/api-docs/**", "/v2/api-docs/**", "/swagger-resources/**")
						.permitAll()
						.mvcMatchers(HttpMethod.GET, "/api/**").permitAll()
						.mvcMatchers("/oauth2/**").permitAll()
						.mvcMatchers(HttpMethod.POST, "/api/auth/**").permitAll()
						.anyRequest().authenticated());
		//				.oauth2Login()
		//				.authorizationEndpoint()
		//				.authorizationRequestRepository(cookieAuthorizationRequestRepository())
		//				.and()
		//				.userInfoEndpoint()
		//				.userService(oauthUserService)
		//				.and()
		//				.successHandler(oAuth2AuthenticationSuccessHandler)
		//				.failureHandler(oAuth2AuthenticationFailureHandler)
		//				.and()
		//				.logout(logout -> logout
		//						.logoutUrl("/api/logout")
		//						.logoutSuccessHandler(logoutSuccessHandler))
		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		http.cors();
		return http.build();
	}

	/*
	By default, Spring OAuth2 uses HttpSessionOAuth2AuthorizationRequestRepository to save
	the authorization request. But, since our service is stateless, we can't save it in
	the session. We'll save the request in a Base64 encoded cookie instead.
	*/
	@Bean
	public HttpCookieOAuth2AuthorizationRequestRepository cookieAuthorizationRequestRepository() {
		return new HttpCookieOAuth2AuthorizationRequestRepository();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(
			final AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

}
