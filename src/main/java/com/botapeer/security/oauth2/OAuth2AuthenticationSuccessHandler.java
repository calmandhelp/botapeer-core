package com.botapeer.security.oauth2;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.botapeer.util.CookieUtils;

@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Autowired
	OAuth2AuthenticationSuccessHandler() {
		System.out.println("successHandler");
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		String targetUrl = determineTargetUrl(request, response, authentication);
		System.out.println(authentication);
		System.out.println("success");
	}

	protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) {
		Cookie cookies[] = request.getCookies();
		System.out.println("cookies: " + cookies[0].getValue());
		Optional<String> redirectUri = CookieUtils.getCookie(request, "redirect_uri")
				.map(Cookie::getValue);
		System.out.println("redirectUri: " + redirectUri);
		return "";
	}

	protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {

	}

	private boolean isAuthorizedRedirectUri(String uri) {
		return false;
	}
}
