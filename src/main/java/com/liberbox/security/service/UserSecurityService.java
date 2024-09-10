package com.liberbox.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserSecurityService {


	private final HttpServletRequest request;

	@Autowired
	public UserSecurityService(HttpServletRequest request) {
		this.request = request;
	}

	public String getUser() {
		var context = SecurityContextHolder.getContext();
		return context.getAuthentication().getName();
	}

	public boolean isAuthenticated() {

		if (request.getServletPath().contains("/swagger-ui/") ||
				request.getServletPath().contains("/actuator/")) {
			return true;
		}

		if (getUser().equals("anonymousUser")) {
			return false;
		}

		if (getUser().equals("anonymousUser")) {
			return false;
		}
		var context = SecurityContextHolder.getContext();
		boolean authenticated = context.getAuthentication().isAuthenticated();
		return authenticated;
	}

	public boolean isNotAuthenticated() {
		return !isAuthenticated();
	}

}
