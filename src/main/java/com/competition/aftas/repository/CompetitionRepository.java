package com.competition.aftas.repository;
import com.competition.aftas.domain.Competition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long> {
    Page<Competition> findAll(Pageable pageable);
    List<Competition> findByDate(Date date);

}
