package com.competition.aftas.DTO.response;

import com.competition.aftas.enums.TokenType;
import lombok.Builder;

@Builder
public record TokenDto(
        String token,
        TokenType tokenType
) {
}
