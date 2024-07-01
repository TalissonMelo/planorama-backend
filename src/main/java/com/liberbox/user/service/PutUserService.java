package com.liberbox.user.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.liberbox.user.controller.request.UpdateUserRequest;
import com.liberbox.user.controller.response.UserResponse;
import com.liberbox.user.domain.User;
import com.liberbox.user.domain.mapper.UserMapper;
import com.liberbox.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class PutUserService {

	private final UserRepository repository;

	public UserResponse execute(String userId, UpdateUserRequest request) {
		User user = repository.findByIdAndActive(userId)
				.orElseThrow(() -> new IllegalArgumentException("User with ID: " + userId + " does not exist"));

		user.toUpdated(request.nickname(), request.phone());

		repository.save(user);

		return UserMapper.extracted(user);
	}

}
