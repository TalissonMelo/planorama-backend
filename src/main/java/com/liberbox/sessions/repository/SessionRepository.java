package com.liberbox.sessions.repository;

import com.liberbox.sessions.domain.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SessionRepository extends JpaRepository<Session, String> {

    @Query("SELECT s FROM Session s WHERE s.scheduleId = :scheduleId")
    List<Session> findByScheduleId(String scheduleId);

    @Query("SELECT s.legendId FROM Session s WHERE s.scheduleId = :scheduleId")
    List<String> findLegendIdByScheduleId(String scheduleId);


    @Query(value = "SELECT * FROM session WHERE schedule_id = :scheduleId AND DATE(start_time) = :date", nativeQuery = true)
    List<Session> findByScheduleIdAndDate(String scheduleId, LocalDate date);
}
