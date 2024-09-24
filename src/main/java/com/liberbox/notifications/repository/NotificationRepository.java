package com.liberbox.notifications.repository;

import com.liberbox.notifications.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, String> {


    @Query("SELECT n FROM Notification n WHERE n.receivedId = :userId ")
    List<Notification> findByUserId(String userId);
}
