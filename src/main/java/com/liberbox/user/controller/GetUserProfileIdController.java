package com.liberbox.user.controller;

import com.liberbox.user.controller.response.UserProfileResponse;
import com.liberbox.user.controller.response.UserResponse;
import com.liberbox.user.service.GetUserIdService;
import com.liberbox.user.service.GetUserProfileIdService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "user")
@RestController
@RequiredArgsConstructor
public class GetUserProfileIdController {

	private final GetUserProfileIdService service;

	@GetMapping("/v1/users/profiles")
	public ResponseEntity<UserProfileResponse> execute() {

		UserProfileResponse response = service.execute();

		return ResponseEntity.status(200).body(response);

	}
}
