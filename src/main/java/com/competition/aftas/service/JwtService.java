package com.competition.aftas.service;


import com.competition.aftas.domain.User;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public interface JwtService {
//    private static final String SECRET_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";

    Key getSinginKey();

    String extractUserEmail(String jwt);

    Claims extractAllClaims(String jwt);

    <T> T extractClaim(String jwt, Function<Claims,T> claimsResolver);

    String generateToken( User userDetails);

    Boolean validateToken(String jwt, UserDetails userDetails);

    boolean isTokenExpired(String jwt);

    Date extractExpiration(String jwt);
}
