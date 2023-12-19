package com.competition.aftas.DTO;
import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class RankingDTO {
    private Long memberId;
    private Long competitionId;
    private Integer rank;
    private int score;

}
