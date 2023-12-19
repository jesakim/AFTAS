package com.competition.aftas.service.impl;
import com.competition.aftas.DTO.LevelDTO;
import com.competition.aftas.domain.Level;
import com.competition.aftas.repository.LevelRepository;
import com.competition.aftas.service.LevelService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LevelServiceImpl implements LevelService {

    private final LevelRepository levelRepository;

    @Autowired
    public LevelServiceImpl(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }

    @Override
    public LevelDTO createLevel(LevelDTO levelDTO) {
        Level level = new Level();
        BeanUtils.copyProperties(levelDTO, level);
        levelRepository.save(level);
        return levelDTO;
    }

    @Override
    public LevelDTO getLevelById(Integer id) {
        Level level = levelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Level not found with id: " + id));
        LevelDTO levelDTO = new LevelDTO();
        BeanUtils.copyProperties(level, levelDTO);
        return levelDTO;
    }

    @Override
    public List<LevelDTO> getAllLevels() {
        List<Level> levels = levelRepository.findAll();
        return levels.stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public LevelDTO updateLevel(Integer id, LevelDTO levelDTO) {
        Level existingLevel = levelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Level not found with id: " + id));
        BeanUtils.copyProperties(levelDTO, existingLevel);
        existingLevel = levelRepository.save(existingLevel);
        BeanUtils.copyProperties(existingLevel, levelDTO);
        return levelDTO;
    }

    @Override
    public void deleteLevel(Integer id) {
        levelRepository.deleteById(id);
    }

    private LevelDTO convertEntityToDTO(Level level) {
        LevelDTO levelDTO = new LevelDTO();
        BeanUtils.copyProperties(level, levelDTO);
        return levelDTO;
    }
}
