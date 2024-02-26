package com.competition.aftas.controller;

import com.competition.aftas.DTO.request.LoginDto;
import com.competition.aftas.DTO.request.RegisterDto;
import com.competition.aftas.DTO.response.TokenDto;
import com.competition.aftas.service.AuthService;
import com.competition.aftas.utils.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Response<TokenDto>> register(@Valid @RequestBody RegisterDto registerDto) {
        return ResponseEntity.ok(authService.register(registerDto));
    }

    @PostMapping("/login")
    public ResponseEntity<Response<TokenDto>> login(@Valid @RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(authService.login(loginDto));
    }

    @PostMapping("/refresh")
    public ResponseEntity<Response<TokenDto>> refresh(@Valid @RequestBody com.competition.aftas.DTO.request.TokenDto tokenDto) {
        return ResponseEntity.ok(authService.refreshToken(tokenDto));
    }
}

