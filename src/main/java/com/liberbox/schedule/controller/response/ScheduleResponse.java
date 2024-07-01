package com.liberbox.schedule.controller.response;

import java.time.LocalTime;

public record ScheduleResponse(String id, String name, LocalTime startTime, LocalTime endTime) {
}
