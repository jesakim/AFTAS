package com.competition.aftas.service;
import com.competition.aftas.DTO.HuntingDTO;
import com.competition.aftas.domain.Competition;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public interface HuntingService {
    HuntingDTO getHuntingById(Integer id);
    List<HuntingDTO> getAllHuntings();
    HuntingDTO createHunting(HuntingDTO huntingDTO);
    HuntingDTO updateHunting(Integer id, HuntingDTO updatedHuntingDTO);
    void deleteHunting(Integer id);
    void calculateAndAssignScores(Competition competition);
}
