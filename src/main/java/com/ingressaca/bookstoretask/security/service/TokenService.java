package com.ingressaca.bookstoretask.security.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;


    public String generateToken(String username) {
        Date creationDate = new Date(System.currentTimeMillis());

        return Jwts.builder()
                .setSubject(username)
                .setIssuer("bookStoreTask")
                .setIssuedAt(creationDate)
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean tokenValidate(String token) {
        if (getUsernameFromToken(token) != null && isExpired(token)) {
            return true;
        }
        return false;
    }

    public String getUsernameFromToken(String token) {
        Claims claims = getClaims(token);
        return claims.getSubject();

    }

    public boolean isExpired(String token) {
        Date currentTime = new Date(System.currentTimeMillis());

        Claims claims = getClaims(token);
        return claims.getExpiration().after(currentTime);
    }

    private Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

}
