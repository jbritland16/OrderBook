package com.tradeteam.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.tradeteam.entities.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.springframework.security.config.Elements.JWT;

@Service
public class TokenService {
    public String generateToken(User user) {
        return JWT.create()
                .withIssuer("Order")
                .withSubject(user.getUsername())
                .withClaim("userId", user.getUserId())
                .withExpiresAt(LocalDateTime.now()
                        .plusMinutes(30)
                        .toInstant(ZoneOffset.of("+02:00"))
                ).sign(Algorithm.HMAC256("secret"));
    }


    public String getSubject(String token) {
        return JWT.require(Algorithm.HMAC256("secret"))
                .withIssuer("")
                .build().verify(token).getSubject();

    }
}