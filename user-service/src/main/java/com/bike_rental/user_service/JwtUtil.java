package com.bike_rental.user_service;

import io.jsonwebtoken.Jwts;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private final JwtProvider jwtProvider;
    private final Key key;
    private final long JWT_TOKEN_VALIDITY;

    public JwtUtil(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
        this.key = jwtProvider.getSecretKey();
        this.JWT_TOKEN_VALIDITY = jwtProvider.getJwtTokenValidity();
    }

    public String generateToken(UserDetails user) {

        Map<String, Object> claims = new HashMap<>();
        for(GrantedAuthority authority : user.getAuthorities()) {
            claims.put("role", authority.getAuthority());
        }

        return Jwts.builder()
                .setSubject(user.getUsername())
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(key)
                .compact();
    }

}
