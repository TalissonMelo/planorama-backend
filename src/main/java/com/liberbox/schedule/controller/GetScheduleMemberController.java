package com.liberbox.schedule.controller;

import com.liberbox.schedule.controller.response.ScheduleDailyResponse;
import com.liberbox.schedule.controller.response.ScheduleResponse;
import com.liberbox.schedule.service.GetScheduleMemberService;
import com.liberbox.schedule.service.GetScheduleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "schedule")
@RestController
@RequiredArgsConstructor
public class GetScheduleMemberController {

    private final GetScheduleMemberService service;

    @GetMapping("/v1/schedules")
    public ResponseEntity<List<ScheduleDailyResponse>> execute() {

        List<ScheduleDailyResponse> response = service.execute();

        return ResponseEntity.status(200).body(response);

    }
}
