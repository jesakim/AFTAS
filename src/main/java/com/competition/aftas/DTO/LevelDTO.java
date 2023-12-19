package com.competition.aftas.DTO;
import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class LevelDTO {
    private Integer id;
    private Integer code;
    private String description;
    private Integer points;
}
