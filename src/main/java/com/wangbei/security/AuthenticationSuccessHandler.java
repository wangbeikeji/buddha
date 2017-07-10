package com.wangbei.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		if ("false".equals(request.getParameter("form"))) {
			response.getWriter().println("login successful!");
		} else {
			setDefaultTargetUrl("/");
			setAlwaysUseDefaultTargetUrl(true);
			super.onAuthenticationSuccess(request, response, authentication);
		}
	}

}
