package com.competition.aftas.domain;
import jakarta.persistence.*;
import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Hunting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer numberOfFish;
    @ManyToOne
    @JoinColumn(name = "competition_id")
    private Competition competition;
    @ManyToOne
    @JoinColumn(name = "member_num")
    private Member member;
    @ManyToOne
    @JoinColumn(name = "fish_id")
    private Fish fish;


}
