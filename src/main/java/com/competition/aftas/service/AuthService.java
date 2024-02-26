package com.competition.aftas.service;

import com.competition.aftas.DTO.request.LoginDto;
import com.competition.aftas.DTO.request.RegisterDto;
import com.competition.aftas.DTO.response.TokenDto;
import com.competition.aftas.utils.Response;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    Response<TokenDto> register(RegisterDto registerDto);

    Response<TokenDto> login(LoginDto loginDto);

    Response<TokenDto> refreshToken(com.competition.aftas.DTO.request.TokenDto tokenDto);
}
