package com.liberbox.notifications.service;

import com.liberbox.members.controller.response.MemberResponse;
import com.liberbox.notifications.domain.Notification;
import com.liberbox.notifications.repository.NotificationRepository;
import com.liberbox.schedule.domain.Schedule;
import com.liberbox.sessions.domain.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PutSessionNotificationService {

    private final NotificationRepository notificationRepository;

    public void execute(String currentUser, Schedule schedule, Session session, List<MemberResponse> memberResponses) {


        List<Notification> notifications = new ArrayList<>();

        String descriptionPart = session.getDescription() != null && !session.getDescription().isEmpty()
                ? String.format(" A descrição do registro é: '%s'.", session.getDescription())
                : "";

        memberResponses.forEach(userSession -> {


            String description = String.format("Prezado(a) %s, um agendamento foi atualizado na agenda '%s' para o dia %s às %s. O título do registro é: '%s'.%s",
                    userSession.member().nickname(),
                    schedule.getName(),
                    session.getStartTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    session.getStartTime().format(DateTimeFormatter.ofPattern("HH:mm")),
                    session.getTitle(),
                    descriptionPart
            );


            notifications.add(Notification.to(currentUser,
                    schedule.getId(),
                    session.getId(),
                    userSession.member().id(),
                    description));
        });


        notificationRepository.saveAll(notifications);
    }

}
