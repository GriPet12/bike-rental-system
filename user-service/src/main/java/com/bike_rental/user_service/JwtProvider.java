package com.bike_rental.user_service;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;

@Component
public class JwtProvider {

    private final Key key;

    private static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    public JwtProvider(@Value("${jwt.secret}") String secret){
        key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public Key getSecretKey(){
        return key;
    }

    public long getJwtTokenValidity(){
        return  JWT_TOKEN_VALIDITY;
    }

}
