package com.liberbox.schedule.repository;

import com.liberbox.schedule.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, String> {

    @Query("SELECT s FROM Schedule s WHERE s.userId = :currentUser")
    List<Schedule> findByUserId(String currentUser);

    @Query("SELECT s.name FROM Schedule s WHERE s.id = :scheduleId")
    String listById(String scheduleId);
}
