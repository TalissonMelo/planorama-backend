package com.liberbox.notifications.service;

import com.liberbox.config.domain.UserContext;
import com.liberbox.notifications.controller.response.NotificationResponse;
import com.liberbox.notifications.domain.Notification;
import com.liberbox.notifications.repository.NotificationRepository;
import com.liberbox.schedule.domain.Schedule;
import com.liberbox.schedule.repository.ScheduleRepository;
import com.liberbox.sessions.domain.Session;
import com.liberbox.sessions.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetUserNotificationService {

    private final NotificationRepository notificationRepository;

    public List<NotificationResponse> execute() {

        String userId = UserContext.getCurrentUser();

        List<Notification> notifications = notificationRepository.findByUserId(userId);

        return notifications.stream()
                .map(notification -> new NotificationResponse(notification.getId(),
                        notification.getOwnerId(),
                        notification.getScheduleId(),
                        notification.getSessionId(),
                        notification.getDescription()))
                .collect(Collectors.toList());
    }
}
