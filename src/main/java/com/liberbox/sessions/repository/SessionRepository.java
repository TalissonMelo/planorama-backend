package com.liberbox.sessions.repository;

import com.liberbox.sessions.domain.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SessionRepository extends JpaRepository<Session, String> {

    @Query("SELECT s " +
            "FROM Session s " +
            "WHERE s.scheduleId = :scheduleId " +
            "AND MONTH(s.startTime) = :month " +
            "AND YEAR(s.startTime) = :year ")
    List<Session> findByScheduleId(String scheduleId, int month, int year);

    @Query("SELECT s.legendId FROM Session s WHERE s.scheduleId = :scheduleId")
    List<String> findLegendIdByScheduleId(String scheduleId);


    @Query(value = "SELECT * FROM session WHERE schedule_id = :scheduleId AND DATE(start_time) = :date", nativeQuery = true)
    List<Session> findByScheduleIdAndDate(String scheduleId, LocalDate date);

    @Query(value = "SELECT * FROM session WHERE schedule_id IN :scheduleIds AND DATE(start_time) = :date ORDER BY start_time ASC", nativeQuery = true)
    List<Session> findByScheduleIdsAndDate(List<String> scheduleIds, LocalDate date);


    @Query("SELECT s.id " +
            "FROM Session s " +
            "INNER JOIN Schedule sc ON sc.id = s.scheduleId " +
            "WHERE s.legendId = :legendId ")
    List<String> findLegendIdByLegendId(String legendId);
}
