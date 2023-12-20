package com.competition.aftas.controller;
import com.competition.aftas.DTO.MemberDTO;
import com.competition.aftas.DTO.RankingDTO;
import com.competition.aftas.service.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/rankings")
public class RankingController {
    private final RankingService rankingService;
    @Autowired
    public RankingController(RankingService rankingService) {
        this.rankingService = rankingService;
    }
    @PostMapping
    public ResponseEntity<RankingDTO> createRanking(@RequestBody RankingDTO rankingDTO) {
        RankingDTO createdRanking = rankingService.createRanking(rankingDTO);
        return new ResponseEntity<>(createdRanking, HttpStatus.CREATED);
    }
    @GetMapping("/competition/{competitionI")
    public ResponseEntity<List<RankingDTO>> getRankingsByCompetition(@PathVariable Long competitionId) {
        List<RankingDTO> rankings = rankingService.getRankingsByCompetition(competitionId);
        return ResponseEntity.ok(rankings);
    }
    @GetMapping("/{competitionId}/members")
    public ResponseEntity<List<MemberDTO>> getAllMembersByCompetition(@PathVariable Long competitionId) {
        List<MemberDTO> members = rankingService.getAllMembersByCompetition(competitionId);
        return ResponseEntity.ok(members);
}
}
