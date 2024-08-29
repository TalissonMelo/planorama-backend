package com.liberbox.user.service;

import com.liberbox.config.domain.UserContext;
import com.liberbox.user.controller.response.UserProfileResponse;
import com.liberbox.user.controller.response.UserResponse;
import com.liberbox.user.domain.User;
import com.liberbox.user.domain.mapper.UserMapper;
import com.liberbox.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserProfileIdService {

	private final UserRepository repository;

	public UserProfileResponse execute() {

		String userId = UserContext.getCurrentUser();

		User user = repository.findByIdAndActive(userId)
				.orElseThrow(() -> new IllegalArgumentException("User with ID: " + userId + " does not exist"));

		return new UserProfileResponse(user.getId(), user.getProfiles());
	}

}
