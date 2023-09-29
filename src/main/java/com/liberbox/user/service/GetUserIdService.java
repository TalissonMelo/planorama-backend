package com.liberbox.user.service;

import org.springframework.stereotype.Service;

import com.liberbox.user.controller.response.UserResponse;
import com.liberbox.user.domain.User;
import com.liberbox.user.domain.mapper.UserMapper;
import com.liberbox.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetUserIdService {

	private final UserRepository repository;

	public UserResponse execute(String userId) {

		User user = repository.findByIdAndActive(userId)
				.orElseThrow(() -> new IllegalArgumentException("User with ID: " + userId + " does not exist"));

		return UserMapper.extracted(user);
	}

}
