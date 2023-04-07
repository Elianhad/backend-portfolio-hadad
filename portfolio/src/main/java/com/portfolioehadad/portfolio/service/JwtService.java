package com.portfolioehadad.portfolio.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
@Component
public class JwtService  {
    private final String REQ_HEADER = "Authorization";
    private final String REQ_HEADER_PREFIX = "Bearer ";
    private final Key SECRET= Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String createJwt( String email ){
         return Jwts
                .builder()
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SECRET).compact();
    }
    public boolean getAuthentication(String token, String email){
        try {
            Claims claims = extractclaims(token);
            return claims.getSubject().matches(email);
        } catch (JwtException e){
            return  false;
        }
    }
    private Claims extractclaims (String token){
        return Jwts.parserBuilder().setSigningKey(SECRET).build().parseClaimsJws(token).getBody();
    }
}
