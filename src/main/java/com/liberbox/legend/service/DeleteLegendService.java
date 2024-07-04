package com.liberbox.legend.service;

import com.liberbox.legend.repostiory.LegendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteLegendService {

    private final LegendRepository repository;

    public void execute(String legendId) {

        repository.deleteById(legendId);
    }

}
