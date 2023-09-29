package com.liberbox.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.liberbox.user.service.DeleteUserIdService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "user")
@RestController
@RequiredArgsConstructor
public class DeleteUserIdController {

	private final DeleteUserIdService service;

	@DeleteMapping("/v1/users/{userId}")
	public ResponseEntity<Void> execute(@PathVariable String userId) {

		service.execute(userId);

		return ResponseEntity.noContent().build();

	}
}
