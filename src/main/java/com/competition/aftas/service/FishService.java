package com.competition.aftas.service;
import com.competition.aftas.DTO.FishDTO;
import com.competition.aftas.domain.Fish;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public interface FishService {
    FishDTO saveFish(FishDTO fishDTO);
    Fish getFishById(Integer id);
    List<FishDTO> getAllFish();
    FishDTO updateFish(Integer id, FishDTO updatedFishDTO);
    void deleteFish(Integer id);
}
