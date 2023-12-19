package com.competition.aftas.service.impl;
import com.competition.aftas.DTO.HuntingDTO;
import com.competition.aftas.DTO.RankingDTO;
import com.competition.aftas.domain.Competition;
import com.competition.aftas.domain.Fish;
import com.competition.aftas.domain.Hunting;
import com.competition.aftas.domain.Member;
import com.competition.aftas.repository.HuntingRepository;
import com.competition.aftas.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HuntingServiceImpl implements HuntingService {

    @Autowired
    private RankingService rankingService;
    private final HuntingRepository huntingRepository;
    private final CompetitionService competitionService;
    private final FishService fishService;
    private final MemberService memberService;
    @Autowired
    public HuntingServiceImpl(HuntingRepository huntingRepository, CompetitionService competitionService,
                              FishService fishService, MemberService memberService) {
        this.huntingRepository = huntingRepository;
        this.competitionService = competitionService;
        this.fishService = fishService;
        this.memberService = memberService;
    }
    @Override
    public HuntingDTO getHuntingById(Integer id) {
        Optional<Hunting> optionalHunting = huntingRepository.findById(id);
        return optionalHunting.map(this::convertEntityToDTO).orElse(null);
    }
    @Override
    public List<HuntingDTO> getAllHuntings() {
        return huntingRepository.findAll().stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }
@Override
public HuntingDTO createHunting(HuntingDTO huntingDTO) {
    Competition competition = competitionService.getCompetitionById(Long.valueOf(huntingDTO.getCompetitionId()));
    Fish fish = fishService.getFishById(huntingDTO.getFishId());
    Member member = memberService.getMemberById(huntingDTO.getMemberNum());
    if (competition == null || fish == null || member == null) {
        return null;
    }

    Hunting existingHunting = huntingRepository.findByMemberAndCompetitionAndFish(member, competition, fish);

    if (existingHunting != null) {
        existingHunting.setNumberOfFish(existingHunting.getNumberOfFish() + huntingDTO.getNumberOfFish());
        Hunting updatedHunting = huntingRepository.save(existingHunting);
        return convertEntityToDTO(updatedHunting);
    } else {
        Hunting hunting = mapDTOToHunting(huntingDTO);
        hunting.setCompetition(competition);
        hunting.setFish(fish);
        hunting.setMember(member);
        Hunting savedHunting = huntingRepository.save(hunting);
        return convertEntityToDTO(savedHunting);
    }
}

    @Override
    public HuntingDTO updateHunting(Integer id, HuntingDTO updatedHuntingDTO) {
        Optional<Hunting> optionalHunting = huntingRepository.findById(id);
        if (optionalHunting.isPresent()) {
            Hunting existingHunting = optionalHunting.get();
            BeanUtils.copyProperties(updatedHuntingDTO, existingHunting);
            Hunting updatedHunting = huntingRepository.save(existingHunting);
            return convertEntityToDTO(updatedHunting);
        }
        return null;
    }

    @Override
    public void deleteHunting(Integer id) {
        huntingRepository.deleteById(id);
    }

    private HuntingDTO convertEntityToDTO(Hunting hunting) {
        HuntingDTO huntingDTO = new HuntingDTO();
        BeanUtils.copyProperties(hunting, huntingDTO, "competition", "fish", "member");

        if (hunting.getCompetition() != null) {
            huntingDTO.setCompetitionId(Math.toIntExact(hunting.getCompetition().getId()));
        }
        if (hunting.getFish() != null) {
            huntingDTO.setFishId(hunting.getFish().getId());
        }
        if (hunting.getMember() != null) {
            huntingDTO.setMemberNum(hunting.getMember().getNum());
        }
        return huntingDTO;
    }
    private Hunting mapDTOToHunting(HuntingDTO huntingDTO) {
        Hunting hunting = new Hunting();
        BeanUtils.copyProperties(huntingDTO, hunting, "competition", "fish", "member");
        return hunting;
    }
@Override
    public void calculateAndAssignScores(Competition competition) {
        List<Hunting> huntingEntries = huntingRepository.findByCompetition(competition);
        Map<Member, Integer> memberScores = new HashMap<>();
        for (Hunting hunting : huntingEntries) {
            Member member = hunting.getMember();
            Fish fish = hunting.getFish();
            if (member != null && fish != null) {
                int fishPoints = fish.getLevel().getPoints();
                int scoreToAdd = fishPoints * hunting.getNumberOfFish();
                memberScores.put(member, memberScores.getOrDefault(member, 0) + scoreToAdd);
            }
        }
        memberScores.forEach((member, score) -> {
            RankingDTO rankingDTO = new RankingDTO();
            rankingDTO.setMemberId(Long.valueOf(member.getNum()));
            rankingDTO.setCompetitionId(competition.getId());
            rankingDTO.setScore(score);
            rankingService.updateScore(rankingDTO);
        });
    }
}



