package com.competition.aftas.service.impl;


import com.competition.aftas.DTO.request.LoginDto;
import com.competition.aftas.DTO.request.RegisterDto;
import com.competition.aftas.DTO.response.TokenDto;
import com.competition.aftas.domain.Authority;
import com.competition.aftas.domain.Token;
import com.competition.aftas.domain.User;
import com.competition.aftas.enums.Role;
import com.competition.aftas.enums.TokenType;
import com.competition.aftas.repository.AuthorityRepository;
import com.competition.aftas.repository.TokenRepository;
import com.competition.aftas.repository.UserRepository;
import com.competition.aftas.service.JwtService;
import com.competition.aftas.utils.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.competition.aftas.service.AuthService;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final TokenRepository tokenRepository;

    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public Response<TokenDto> register(RegisterDto registerDto) {
        Authority authority = authorityRepository.findById(4L).orElseThrow(() -> new RuntimeException("Authority not found"));

        Set<Authority> authorities = Set.of(authority);
        userRepository.findByEmail(registerDto.email()).ifPresent(user -> {
            throw new IllegalArgumentException("User with email " + registerDto.email() + " already exists");
        });
        var user = User.builder()
                .firstName(registerDto.firstName())
                .lastName(registerDto.lastName())
                .email(registerDto.email())
                .password(passwordEncoder.encode(registerDto.password()))
                .role(Role.MEMBER)
                .authorities(authorities)
                .build();
        userRepository.save(user);


        return saveAndGetTokenResponse(user);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    private Response<TokenDto> saveAndGetTokenResponse(User user) {
        revokeAllUserTokens(user);
        String jwt = jwtService.generateToken(user);

        var token = Token.builder()
                .token(jwt)
                .user(user)
                .tokenType(TokenType.BEARER)
                .isRevoked(false)
                .isExpired(false)
                .build();
        tokenRepository.save(token);

        var tokenDto = TokenDto.builder()
                .token(jwt)
                .tokenType(TokenType.BEARER)
                .build();

        return Response.<TokenDto>
                builder().
                result(tokenDto).
                build();
    }

    @Override
    public Response<TokenDto> login(LoginDto loginDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.email(),
                        loginDto.password()
                )
        );
        User user = userRepository.findByEmail(loginDto.email()).orElseThrow(() -> new RuntimeException("User with email " + loginDto.email() + " not found"));
        return saveAndGetTokenResponse(user);
    }

    @Override
    public Response<TokenDto> refreshToken(com.competition.aftas.DTO.request.TokenDto tokenDto) {
        Token userToken = tokenRepository.findByToken(tokenDto.token()).orElseThrow(() -> new RuntimeException("Token not found"));
        if (userToken.isRevoked() || userToken.isExpired())
            throw new RuntimeException("Token is revoked or expired");
        User user = userToken.getUser();
        return saveAndGetTokenResponse(user);
    }

}
