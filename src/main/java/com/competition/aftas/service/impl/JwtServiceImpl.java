package com.competition.aftas.service.impl;


import com.competition.aftas.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import com.competition.aftas.service.JwtService;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;
@Service
public class JwtServiceImpl implements JwtService {
    private static final String SECRET_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";


    @Override
    public Key getSinginKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public String extractUserEmail(String jwt) {
        return extractClaim(jwt, Claims::getSubject);
    }

    @Override
    public Claims extractAllClaims(String jwt) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSinginKey())
                .build()
                .parseClaimsJws(jwt)
                .getBody();
    }


    @Override
    public <T> T extractClaim(String jwt, Function<Claims,T> claimsResolver) {
        final Claims claims = extractAllClaims(jwt);
        return claimsResolver.apply(claims);
    }


    @Override
    public String generateToken( User userDetails) {
        return Jwts
                .builder()
                .setSubject(userDetails.getUsername())
                .claim("authorities", userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toArray())
                .claim("role", userDetails.getRole())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(getSinginKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    @Override
    public Boolean validateToken(String jwt, UserDetails userDetails) {
        final String userEmail = extractUserEmail(jwt);
        return (userEmail.equals(userDetails.getUsername()) && !isTokenExpired(jwt));
    }


    @Override
    public boolean isTokenExpired(String jwt) {
        return extractExpiration(jwt).before(new Date());
    }


    @Override
    public Date extractExpiration(String jwt) {
        return extractClaim(jwt, Claims::getExpiration);
    }
}
