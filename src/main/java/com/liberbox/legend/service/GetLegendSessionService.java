package com.liberbox.legend.service;

import com.liberbox.legend.controller.response.LegendResponse;
import com.liberbox.legend.domain.Legend;
import com.liberbox.legend.repostiory.LegendRepository;
import com.liberbox.sessions.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetLegendSessionService {

    private final SessionRepository sessionRepository;
    private final LegendRepository legendRepository;

    public List<LegendResponse> execute(String scheduleId) {

        List<String> legendIds = sessionRepository.findLegendIdByScheduleId(scheduleId);

        List<Legend> legends = legendRepository.findAllById(legendIds);

        return legends.stream().map(legend -> toLegend(legend))
                .sorted(Comparator.comparing(legendResponse -> legendResponse.description().length()))
                .collect(Collectors.toList());

    }

    private LegendResponse toLegend(Legend legend) {
        return new LegendResponse(legend.getId(), legend.getOwnerId(), legend.getColor(), legend.getDescription());
    }

}
