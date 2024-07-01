package com.liberbox.user.domain.mapper;

import com.liberbox.user.controller.response.UserResponse;
import com.liberbox.user.domain.User;

public class UserMapper {

	public static UserResponse extracted(User user) {
		return new UserResponse(user.getId(), user.getEmail(), user.getNickname(), user.getPhone());
	}
}
