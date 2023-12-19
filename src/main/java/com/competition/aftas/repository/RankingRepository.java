package com.competition.aftas.repository;
import com.competition.aftas.domain.Member;
import com.competition.aftas.domain.MemberCompetitionId;
import com.competition.aftas.domain.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.*;
import java.util.List;
@Repository

public interface RankingRepository extends JpaRepository<Ranking, MemberCompetitionId> {
    List<Ranking> findByIdCompetitionId(Long competitionId);
    @Query("SELECT DISTINCT r.id.member FROM Ranking r WHERE r.id.competition.id = :competitionId")
    List<Member> findMembersByCompetitionId(@Param("competitionId") Long competitionId);


}