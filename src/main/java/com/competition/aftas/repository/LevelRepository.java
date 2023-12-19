package com.competition.aftas.repository;
import com.competition.aftas.domain.Level;
import org.springframework.data.jpa.repository.JpaRepository;
public interface LevelRepository extends JpaRepository<Level, Integer> {
    Level getById(Integer code);
}
