package com.competition.aftas.repository;
import com.competition.aftas.domain.Fish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface FishRepository extends JpaRepository<Fish, Integer> {
}
