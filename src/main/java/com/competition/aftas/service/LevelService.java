package com.competition.aftas.service;
import com.competition.aftas.DTO.LevelDTO;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public interface LevelService {
    LevelDTO createLevel(LevelDTO levelDTO);
    LevelDTO getLevelById(Integer id);
    List<LevelDTO> getAllLevels();
    LevelDTO updateLevel(Integer id, LevelDTO levelDTO);
    void deleteLevel(Integer id);
}
