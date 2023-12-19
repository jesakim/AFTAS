package com.competition.aftas.service.impl;
import com.competition.aftas.DTO.RankingDTO;
import com.competition.aftas.domain.Competition;
import com.competition.aftas.domain.Fish;
import com.competition.aftas.domain.Hunting;
import com.competition.aftas.domain.Level;
import com.competition.aftas.domain.Member;
import com.competition.aftas.repository.HuntingRepository;
import com.competition.aftas.service.RankingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.mockito.Mockito.*;

class HuntingServiceImplTest {

    @Mock
    private HuntingRepository huntingRepository;

    @Mock
    private RankingService rankingService;

    @InjectMocks
    private HuntingServiceImpl huntingService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void calculateAndAssignScores() {

        Competition competition = new Competition();
        Member member = new Member();
        Fish fish = new Fish();
        Level level = new Level();

        level.setPoints(10);
        fish.setLevel(level);
        member.setNum(1);
        Hunting hunting = new Hunting();
        hunting.setNumberOfFish(5);
        hunting.setMember(member);
        hunting.setFish(fish);

        List<Hunting> huntingEntries = Arrays.asList(hunting);

        when(huntingRepository.findByCompetition(competition)).thenReturn(huntingEntries);

        huntingService.calculateAndAssignScores(competition);


    }
}
