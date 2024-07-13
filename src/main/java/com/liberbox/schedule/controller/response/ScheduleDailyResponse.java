package com.liberbox.schedule.controller.response;

import com.liberbox.sessions.domain.Session;

import java.time.LocalTime;
import java.util.List;

public record ScheduleDailyResponse(String id, String name, LocalTime startTime, LocalTime endTime,
                                    List<Session> sessions) {
}
