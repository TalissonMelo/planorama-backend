package com.liberbox.notifications.controller.response;

public record NotificationResponse(String id, String ownerId, String scheduleId, String sessionId, String description) {
}
