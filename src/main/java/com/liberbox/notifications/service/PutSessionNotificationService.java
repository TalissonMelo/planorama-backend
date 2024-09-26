package com.liberbox.notifications.service;

import com.liberbox.members.controller.response.MemberResponse;
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
public class PutSessionNotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    public void execute(String currentUser, Schedule schedule, Session session, List<MemberResponse> memberResponses) {

        List<User> users = userRepository.findAllById(memberResponses.stream().map(memberResponse -> memberResponse.member().id()).collect(Collectors.toList()));


        List<Notification> notifications = new ArrayList<>();

        String descriptionPart = session.getDescription() != null && !session.getDescription().isEmpty()
                ? String.format(" A descrição do registro é: '%s'.", session.getDescription())
                : "";

        users.forEach(userSession -> {


            String description = String.format("Prezado(a) %s, um agendamento foi atualizado na agenda '%s' para o dia %s às %s. O título do registro é: '%s'.%s",
                    userSession.getNickname(),
                    schedule.getName(),
                    session.getStartTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    session.getStartTime().format(DateTimeFormatter.ofPattern("HH:mm")),
                    session.getTitle(),
                    descriptionPart
            );


            notifications.add(Notification.to(currentUser,
                    schedule.getId(),
                    session.getId(),
                    userSession.getId(),
                    description));
        });


        notificationRepository.saveAll(notifications);
    }

}
