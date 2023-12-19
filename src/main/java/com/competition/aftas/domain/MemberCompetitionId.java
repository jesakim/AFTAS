package com.competition.aftas.domain;
import jakarta.persistence.Embeddable;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Embeddable
public class MemberCompetitionId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "member_num", referencedColumnName = "num")
    private Member member;
    @ManyToOne
    @JoinColumn(name = "competition_id", referencedColumnName = "id")
    private Competition competition;

}