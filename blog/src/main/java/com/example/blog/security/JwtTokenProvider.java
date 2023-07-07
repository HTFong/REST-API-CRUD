package com.example.blog.security;

import com.example.blog.exception.BlogAPIException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${app-jwt-secret}")
    private String jwtSecret;
    @Value("${app-jwt-expiration-milliseconds}")
    private long jwtExpirationDate;

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(currentDate)
                .setExpiration(expireDate)
                .signWith(getRawSecretKey())
                .compact();
        return token;
    }
    private Key getRawSecretKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }
    public String getUsername(String token) {
        Claims payload = Jwts.parserBuilder().setSigningKey(getRawSecretKey()).build()
                .parseClaimsJws(token)
                .getBody();
        String username = payload.getSubject();
        return username;
    }
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getRawSecretKey()).build()
                    .parse(token);
        }catch (MalformedJwtException ex) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Invalid JWT");
        }catch (ExpiredJwtException ex) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Expired JWT");
        }catch (UnsupportedJwtException ex) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Unsupported JWT");
        }catch (IllegalArgumentException ex) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"JWT claims string is empty");
        }
        return true;
    }
}
