package com.competition.aftas.controller;
import com.competition.aftas.DTO.LevelDTO;
import com.competition.aftas.service.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/levels")
public class LevelController {
    private final LevelService levelService;
    @Autowired
    public LevelController(LevelService levelService) {
        this.levelService = levelService;
    }
    @PostMapping
    public ResponseEntity<LevelDTO> createLevel(@RequestBody LevelDTO levelDTO) {
        LevelDTO createdLevel = levelService.createLevel(levelDTO);
        return new ResponseEntity<>(createdLevel, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<LevelDTO> getLevelById(@PathVariable Integer id) {
        LevelDTO levelDTO = levelService.getLevelById(id);
        return ResponseEntity.ok(levelDTO);
    }
    @GetMapping
    public ResponseEntity<List<LevelDTO>> getAllLevels() {
        List<LevelDTO> levels = levelService.getAllLevels();
        return ResponseEntity.ok(levels);
    }
    @PutMapping("/{id}")
    public ResponseEntity<LevelDTO> updateLevel(@PathVariable Integer id, @RequestBody LevelDTO levelDTO) {
        LevelDTO updatedLevel = levelService.updateLevel(id, levelDTO);
        return ResponseEntity.ok(updatedLevel);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLevel(@PathVariable Integer id) {
        levelService.deleteLevel(id);
        return ResponseEntity.noContent().build();
    }
}
