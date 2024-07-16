package com.liberbox.schedule.controller.response;

import java.util.List;

public record ScheduleFreeTimeResponse(String id, String title, List<ScheduleTimeResponse> times) {
}
