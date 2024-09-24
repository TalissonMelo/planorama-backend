package com.liberbox.notifications.service;

import com.liberbox.config.domain.UserContext;
import com.liberbox.members.domain.Member;
import com.liberbox.notifications.domain.Notification;
import com.liberbox.notifications.repository.NotificationRepository;
import com.liberbox.schedule.domain.Schedule;
import com.liberbox.sessions.domain.Session;
import com.liberbox.user.domain.User;
import com.liberbox.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostSessionNotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    public void execute(List<Member> members, Schedule schedule, Session session) {

        List<User> users = userRepository.findAllById(members.stream().map(member -> member.getUserId()).collect(Collectors.toList()));

        List<Notification> notifications = new ArrayList<>();

        users.forEach(userSession -> {
            String description = String.format("Prezado(a) %s, um novo agendamento foi registrado na agenda '%s' para o dia %s às %s. O título do registro é: '%s'.",
                    userSession.getNickname(),
                    schedule.getName(),
                    session.getStartTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    session.getStartTime().format(DateTimeFormatter.ofPattern("HH:mm")),
                    session.getTitle()
            );


            notifications.add(Notification.to(UserContext.getCurrentUser(),
                    schedule.getId(),
                    session.getId(),
                    userSession.getId(),
                    description));
        });


        notificationRepository.saveAll(notifications);
    }

}
