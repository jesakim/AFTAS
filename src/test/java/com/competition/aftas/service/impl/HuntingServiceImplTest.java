package com.competition.aftas.service.impl;

import com.competition.aftas.domain.*;
import com.competition.aftas.repository.*;
import com.competition.aftas.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
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