package com.liberbox.security.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserSecurityService {

	public String getUser() {
		var context = SecurityContextHolder.getContext();
		return context.getAuthentication().getName();
	}

	public boolean isAuthenticated() {
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
