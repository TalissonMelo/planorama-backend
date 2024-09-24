package com.liberbox.notifications.service;

import com.liberbox.notifications.domain.Notification;
import com.liberbox.notifications.repository.NotificationRepository;
import com.liberbox.schedule.domain.Schedule;
import com.liberbox.schedule.repository.ScheduleRepository;
import com.liberbox.user.domain.User;
import com.liberbox.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostUserNotificationService {

    private final NotificationRepository notificationRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    public void execute(String currentUser, String userId, String scheduleId) {

        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new IllegalArgumentException("Schedule does not exist"));

        List<User> users = userRepository.findAllById(Arrays.asList(currentUser, userId));

        User invitedUser = users.stream().filter(user -> user.getId().equals(userId)).findFirst().orElseThrow();
        User invitingUser = users.stream().filter(user -> user.getId().equals(currentUser)).findFirst().orElseThrow();

        String description = String.format("Prezado(a) %s, o usuário %s convida você para participar da agenda '%s'.",
                invitedUser.getNickname(),
                invitingUser.getNickname(),
                schedule.getName()
        );

        Notification notification = Notification.to(currentUser, scheduleId, null, userId, description);
        notificationRepository.save(notification);
    }


}
