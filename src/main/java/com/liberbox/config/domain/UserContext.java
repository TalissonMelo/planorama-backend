package com.liberbox.config.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserContext {

	private static final ThreadLocal<String> currentUser = new ThreadLocal<>();

	public static String getCurrentUser() {
		return currentUser.get();
	}

	public static void setCurrentUser(String userId) {
		currentUser.set(userId);
	}

	public static void clear() {
		currentUser.remove();
	}
}
