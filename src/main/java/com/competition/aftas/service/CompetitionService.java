package com.competition.aftas.service;

import com.competition.aftas.DTO.CompetitionDTO;
import com.competition.aftas.domain.Competition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public interface CompetitionService {
    CompetitionDTO saveCompetition(CompetitionDTO competitionDTO);
    Competition getCompetitionById(Long id);
    List<CompetitionDTO> getAllCompetitions();
    void deleteCompetition(Long id);
    Page<CompetitionDTO> getAllCompetitions(Pageable pageable);
}
