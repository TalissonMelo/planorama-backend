package com.liberbox.sessions.repository;

import com.liberbox.schedule.domain.Schedule;
import com.liberbox.sessions.domain.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SessionRepository extends JpaRepository<Session, String> {

    @Query("SELECT s FROM Session s WHERE s.scheduleId = :scheduleId")
    List<Session> findByScheduleId(String scheduleId);

    @Query("SELECT s.legendId FROM Session s WHERE s.scheduleId = :scheduleId")
    List<String> findLegendIdByScheduleId(String scheduleId);
}
