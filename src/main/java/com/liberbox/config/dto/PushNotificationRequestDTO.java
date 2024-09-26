package com.liberbox.config.dto;

import com.liberbox.notifications.controller.response.NotificationResponse;

public record PushNotificationRequestDTO(String title, NotificationResponse notification, String token) {
}
