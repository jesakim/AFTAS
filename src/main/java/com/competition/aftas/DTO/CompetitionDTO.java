package com.competition.aftas.DTO;
import lombok.*;
import java.util.Date;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CompetitionDTO {

    private Long id;
    private String code;
    private Date date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Integer numberOfParticipants;
    private String location;
    private Double amount;

}
