package com.competition.aftas.domain;
import jakarta.persistence.*;
import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "ranking")
public class Ranking {
    @EmbeddedId
    private MemberCompetitionId id;

    @Column(name = "ranks")
    private Integer rank;
    private int score;
}
