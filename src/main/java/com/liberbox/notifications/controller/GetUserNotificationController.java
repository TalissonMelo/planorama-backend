package com.liberbox.notifications.controller;


import com.liberbox.notifications.controller.response.NotificationResponse;
import com.liberbox.notifications.service.GetUserNotificationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "notification")
@RestController
@RequiredArgsConstructor
public class GetUserNotificationController {

    private final GetUserNotificationService service;

    @GetMapping("/v1/notifications")
    public ResponseEntity<List<NotificationResponse>> execute() {

        List<NotificationResponse> response = service.execute();

        return ResponseEntity.status(200).body(response);

    }
}
