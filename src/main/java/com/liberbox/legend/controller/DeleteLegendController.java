package com.liberbox.legend.controller;


import com.liberbox.legend.service.DeleteLegendService;
import com.liberbox.sessions.domain.Session;
import com.liberbox.sessions.repository.SessionRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "legend")
@RestController
@RequiredArgsConstructor
public class DeleteLegendController {

    private final DeleteLegendService service;
    private final SessionRepository sessionRepository;

    @DeleteMapping("/v1/legends/{legendId}")
    public ResponseEntity<Void> execute(@PathVariable String legendId) {

        List<String> list = sessionRepository.findLegendIdByLegendId(legendId);

        if (list.size() > 0) {
            throw new IllegalArgumentException("Using legend");
        }

        service.execute(legendId);

        return ResponseEntity.noContent().build();

    }
}
