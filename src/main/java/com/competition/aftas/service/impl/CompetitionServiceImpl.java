package com.competition.aftas.service.impl;// CompetitionServiceImpl.java
import com.competition.aftas.DTO.CompetitionDTO;
import com.competition.aftas.domain.Competition;
import com.competition.aftas.repository.CompetitionRepository;
import com.competition.aftas.service.CompetitionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class CompetitionServiceImpl implements CompetitionService {
    @Autowired
    private CompetitionRepository competitionRepository;
    @Override
    public CompetitionDTO saveCompetition(CompetitionDTO competitionDTO) {
        Date competitionDate = competitionDTO.getDate();
        List<Competition> competitionsOnDate = competitionRepository.findByDate(competitionDate);

        if (!competitionsOnDate.isEmpty()) {
            return null;
        }
        Competition competition = new Competition();
        BeanUtils.copyProperties(competitionDTO, competition);
        Competition savedCompetition = competitionRepository.save(competition);
        BeanUtils.copyProperties(savedCompetition, competitionDTO);
        return competitionDTO;
    }
    @Override
    public Competition getCompetitionById(Long id) {
        Optional<Competition> optionalCompetition = competitionRepository.findById(id);
        return optionalCompetition.orElse(null);
    }
    @Override
    public Page<CompetitionDTO> getAllCompetitions(Pageable pageable) {
        Page<Competition> competitionPage = competitionRepository.findAll(pageable);
        return competitionPage.map(this::convertEntityToDTO);
    }
    @Override
    public List<CompetitionDTO> getAllCompetitions() {
        List<Competition> competitionList = competitionRepository.findAll();
        return competitionList.stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }
    @Override
    public void deleteCompetition(Long id) {
        competitionRepository.deleteById(id);
    }
    private CompetitionDTO convertEntityToDTO(Competition competition) {
        CompetitionDTO competitionDTO = new CompetitionDTO();
        BeanUtils.copyProperties(competition, competitionDTO);
        return competitionDTO;
    }
}
