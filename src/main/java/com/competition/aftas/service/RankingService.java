package com.competition.aftas.service;
import com.competition.aftas.DTO.MemberDTO;
import com.competition.aftas.DTO.RankingDTO;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public interface RankingService {
    RankingDTO createRanking(RankingDTO rankingDTO);
    List<RankingDTO> getRankingsByCompetition(Long competitionId);
    List<MemberDTO> getAllMembersByCompetition(Long competitionId);
    void updateScore(RankingDTO rankingDTO);


}
