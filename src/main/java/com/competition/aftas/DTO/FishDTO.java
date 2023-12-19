package com.competition.aftas.DTO;
import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class FishDTO {
    private Integer id;
    private String name;
    private double averageWeight;
    private LevelDTO level;
}
