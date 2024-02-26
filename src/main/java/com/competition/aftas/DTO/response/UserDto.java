package com.competition.aftas.DTO.response;

import com.competition.aftas.domain.Authority;
import lombok.Builder;

import java.util.Set;

@Builder
public record UserDto(
        Long id,
        String firstName,
        String lastName,
        String email,
        String role,
        Set<Authority> authorities
        ) {
}
