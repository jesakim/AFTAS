package com.competition.aftas.controller;
import com.competition.aftas.DTO.FishDTO;
import com.competition.aftas.domain.Fish;
import com.competition.aftas.service.FishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/fish")
public class FishController {
    private final FishService fishService;
    @Autowired
    public FishController(FishService fishService) {
        this.fishService = fishService;
    }
    @PostMapping
    public ResponseEntity<FishDTO> saveFish(@RequestBody FishDTO fishDTO) {
        FishDTO createdFish = fishService.saveFish(fishDTO);
        return new ResponseEntity<>(createdFish, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Fish> getFishById(@PathVariable Integer id) {
        Fish fishDTO = fishService.getFishById(id);
        return ResponseEntity.ok(fishDTO);
    }
    @GetMapping
    public ResponseEntity<List<FishDTO>> getAllFish() {
        List<FishDTO> fishList = fishService.getAllFish();
        return ResponseEntity.ok(fishList);
    }
    @PutMapping("/{id}")
    public ResponseEntity<FishDTO> updateFish(@PathVariable Integer id, @RequestBody FishDTO updatedFishDTO) {
        FishDTO updatedFish = fishService.updateFish(id, updatedFishDTO);
        return ResponseEntity.ok(updatedFish);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFish(@PathVariable Integer id) {
        fishService.deleteFish(id);
        return ResponseEntity.noContent().build();
    }
}
