package com.liberbox.user.service;

import org.springframework.stereotype.Service;

import com.liberbox.user.domain.User;
import com.liberbox.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeleteUserIdService {

	private final UserRepository repository;

	public void execute(String userId) {

		User user = repository.findByIdAndActive(userId)
				.orElseThrow(() -> new IllegalArgumentException("User with ID: " + userId + " does not exist"));

		user.disabled();
		
		repository.save(user);
	}

}
