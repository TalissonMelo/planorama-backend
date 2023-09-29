package com.liberbox.user.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.liberbox.user.controller.request.ChangePasswordRequest;
import com.liberbox.user.domain.User;
import com.liberbox.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class ChangePasswordUserService {

	private final UserRepository repository;

	public void execute(String userId, ChangePasswordRequest request) {

		User user = repository.findByIdAndActive(userId)
				.orElseThrow(() -> new IllegalArgumentException("User with ID: " + userId + " does not exist"));

		isValidPassword(user.getPassword(), request.oldPassword());

		user.toUpdatePassword(request.newPassword());

		repository.save(user);
	}

	private void isValidPassword(String password, String oldPassword) {
		if (!password.equals(oldPassword)) {
			throw new IllegalArgumentException("The current password is wrong.");
		}

	}

}
