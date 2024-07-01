package com.liberbox.schedule.controller;

import com.liberbox.schedule.service.DeleteScheduleIdService;
import com.liberbox.user.service.DeleteUserIdService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "schedule")
@RestController
@RequiredArgsConstructor
public class DeleteScheduleIdController {

	private final DeleteScheduleIdService service;

	@DeleteMapping("/v1/schedule/{scheduleId}")
	public ResponseEntity<Void> execute(@PathVariable String scheduleId) {

		service.execute(scheduleId);

		return ResponseEntity.noContent().build();

	}
}
