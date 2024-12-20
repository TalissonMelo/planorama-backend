package com.liberbox.schedule.controller;

import com.liberbox.schedule.controller.response.ScheduleDailyResponse;
import com.liberbox.schedule.controller.response.ScheduleResponse;
import com.liberbox.schedule.service.GetScheduleMemberService;
import com.liberbox.schedule.service.GetScheduleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "schedule")
@RestController
@RequiredArgsConstructor
public class GetScheduleMemberController {

    private final GetScheduleMemberService service;

    @GetMapping("/v1/schedules")
    public ResponseEntity<List<ScheduleDailyResponse>> execute(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        List<ScheduleDailyResponse> response = service.execute(date);

        return ResponseEntity.status(200).body(response);

    }
}
