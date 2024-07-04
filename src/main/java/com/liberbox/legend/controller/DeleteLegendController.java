package com.liberbox.legend.controller;


import com.liberbox.legend.service.DeleteLegendService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "legend")
@RestController
@RequiredArgsConstructor
public class DeleteLegendController {

    private final DeleteLegendService service;

    @DeleteMapping("/v1/legends/{legendId}")
    public ResponseEntity<Void> execute(@PathVariable String legendId) {

        service.execute(legendId);

        return ResponseEntity.noContent().build();

    }
}
