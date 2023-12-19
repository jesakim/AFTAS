package com.competition.aftas.service.impl;

import com.competition.aftas.DTO.MemberDTO;
import com.competition.aftas.domain.Competition;
import com.competition.aftas.domain.Member;
import com.competition.aftas.domain.MemberCompetitionId;
import com.competition.aftas.domain.Ranking;
import com.competition.aftas.DTO.RankingDTO;
import com.competition.aftas.repository.RankingRepository;
import com.competition.aftas.repository.MemberRepository;
import com.competition.aftas.repository.CompetitionRepository;
import com.competition.aftas.service.RankingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RankingServiceImpl implements RankingService {

    private final RankingRepository rankingRepository;
    private final MemberRepository memberRepository;
    private final CompetitionRepository competitionRepository;

    @Autowired
    public RankingServiceImpl(
            RankingRepository rankingRepository,
            MemberRepository memberRepository,
            CompetitionRepository competitionRepository) {
        this.rankingRepository = rankingRepository;
        this.memberRepository = memberRepository;
        this.competitionRepository = competitionRepository;
    }

    @Override
    public RankingDTO createRanking(RankingDTO rankingDTO) {
        MemberCompetitionId memberCompetitionId = new MemberCompetitionId();
        Member member = memberRepository.findById(Math.toIntExact(rankingDTO.getMemberId()))
                .orElseThrow(() -> new RuntimeException("Member not found"));
        Competition competition = competitionRepository.findById(rankingDTO.getCompetitionId())
                .orElseThrow(() -> new RuntimeException("Competition not found"));
        memberCompetitionId.setMember(member);
        memberCompetitionId.setCompetition(competition);
        Ranking ranking = Ranking.builder()
                .id(memberCompetitionId)
                .score(0)
                .rank(0)
                .build();
        Ranking savedRanking = rankingRepository.save(ranking);

        return convertEntityToDTO(savedRanking);
    }

    @Override
    public List<RankingDTO> getRankingsByCompetition(Long competitionId) {
        List<Ranking> rankings = rankingRepository.findByIdCompetitionId(competitionId);
        return rankings.stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    private RankingDTO convertEntityToDTO(Ranking ranking) {
        RankingDTO rankingDTO = new RankingDTO();
        BeanUtils.copyProperties(ranking, rankingDTO);
        rankingDTO.setMemberId(Long.valueOf(ranking.getId().getMember().getNum()));
        rankingDTO.setCompetitionId(ranking.getId().getCompetition().getId());
        return rankingDTO;
    }
    @Override
    public List<MemberDTO> getAllMembersByCompetition(Long competitionId) {
        List<Member> members = rankingRepository.findMembersByCompetitionId(competitionId);

        return members.stream()
                .map(this::convertMemberEntityToDTO)
                .collect(Collectors.toList());
    }
    private MemberDTO convertMemberEntityToDTO(Member member) {
        MemberDTO memberDTO = new MemberDTO();

        memberDTO.setNum(member.getNum());
        memberDTO.setName(member.getName());
        memberDTO.setName(member.getFamilyName());
        memberDTO.setName(member.getName());
        return memberDTO;
    }
    @Override
    public void updateScore(RankingDTO rankingDTO) {
        MemberCompetitionId id = new MemberCompetitionId();
        id.setMember(memberRepository.findById(Math.toIntExact(rankingDTO.getMemberId())).orElse(null));
        id.setCompetition(competitionRepository.findById(rankingDTO.getCompetitionId()).orElse(null));
        Ranking ranking = rankingRepository.findById(id).orElse(null);

        if (ranking != null) {
            ranking.setScore(rankingDTO.getScore());
            rankingRepository.save(ranking);
        }
    }

}
