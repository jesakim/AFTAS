package com.competition.aftas.DTO;
import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter

public class HuntingDTO {
    private Integer id;
    private Integer numberOfFish;
    private Integer competitionId;
    private Integer memberNum;
    private Integer fishId;
    private MemberDTO member;
    private CompetitionDTO competition;
    private FishDTO fish;
}
