package com.liberbox.schedule.controller.response;

import java.time.LocalTime;

public record ScheduleResponse(String id, String name, String ownerId, LocalTime startTime, LocalTime endTime) {
}
