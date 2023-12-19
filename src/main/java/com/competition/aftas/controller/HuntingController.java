package com.competition.aftas.controller;
import com.competition.aftas.DTO.HuntingDTO;
import com.competition.aftas.domain.Competition;
import com.competition.aftas.service.CompetitionService;
import com.competition.aftas.service.HuntingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/huntings")
public class HuntingController {
    private final HuntingService huntingService;
    private final CompetitionService competitionService;
    @Autowired
    public HuntingController(HuntingService huntingService, CompetitionService competitionService) {
        this.huntingService = huntingService;
        this.competitionService = competitionService;
    }
    @GetMapping("/{id}")
    public HuntingDTO getHunting(@PathVariable Integer id) {
        return huntingService.getHuntingById(id);
    }
    @GetMapping
    public List<HuntingDTO> getAllHuntings() {
        return huntingService.getAllHuntings();
    }
    @PostMapping
    public HuntingDTO createHunting(@RequestBody HuntingDTO huntingDTO) {
        return huntingService.createHunting(huntingDTO);
    }
    @PutMapping("/{id}")
    public HuntingDTO updateHunting(@PathVariable Integer id, @RequestBody HuntingDTO huntingDTO) {
        return huntingService.updateHunting(id, huntingDTO);
    }
    @DeleteMapping("/{id}")
    public void deleteHunting(@PathVariable Integer id) {
        huntingService.deleteHunting(id);
    }
    @PostMapping("/calculate-scores/{competitionId}")
    public ResponseEntity<String> calculateAndAssignScores(@PathVariable Long competitionId) {
        try {
            Competition competition = competitionService.getCompetitionById(competitionId);
            if (competition != null) {
                huntingService.calculateAndAssignScores(competition);
                return ResponseEntity.ok("Scores calculated and assigned successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Competition not found.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error calculating scores: " + e.getMessage());
        }
    }
}
