package com.liberbox.user.service;

import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
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
	private final PasswordEncoder encoder;

	public void execute(String userId, ChangePasswordRequest request) {

		User user = repository.findByIdAndActive(userId).orElseThrow(() -> new IllegalArgumentException("User with ID: " + userId + " does not exist"));

		isValidPassword(request.oldPassword(), user.getPassword());

		user.toUpdatedPassword(encoder.encode(request.newPassword()));

		repository.save(user);
	}

	private void isValidPassword(String oldPassword, String password) {
		if (!encoder.matches(oldPassword, password)) {
			throw new IllegalArgumentException("The current password is wrong.");
		}

	}

}
