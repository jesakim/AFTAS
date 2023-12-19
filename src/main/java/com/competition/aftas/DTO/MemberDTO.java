package com.competition.aftas.DTO;
import com.competition.aftas.domain.IdentityDocumentType;
import java.util.Date;
import java.util.List;
import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter

public class MemberDTO {
    private Integer num;
    private String name;
    private String familyName;
    private Date accessionDate;
    private String nationality;
    private IdentityDocumentType identityDocument;
    private String identityNumber;
    private List<HuntingDTO> hunts;
}
