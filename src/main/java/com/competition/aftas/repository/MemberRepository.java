package com.competition.aftas.repository;
import com.competition.aftas.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository

public interface MemberRepository extends JpaRepository<Member, Integer> {


}
