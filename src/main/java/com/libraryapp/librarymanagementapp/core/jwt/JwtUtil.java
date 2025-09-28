package com.libraryapp.librarymanagementapp.core.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

@Service
public class JwtUtil {
    @Value("${jwt.secret}")
    private String SECRET_KEY;
    public String generateToken(String userName){

        Date expirationDate = new Date(System.currentTimeMillis()* 1000L * 60 * 60);
        HashMap<String, Object> claims = new HashMap<String, Object>();
        claims.put("userName", userName);
        claims.put("admin", true);

        String jwt = Jwts
                .builder()
                .subject(userName)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(expirationDate)
                .claims(claims)
                .signWith(getSecretKey())
                .compact();
        return jwt;
    }
    public Boolean validateToken(String token){
       try{
           Claims claims = extractAllClaims(token);
           return claims.getExpiration().after(new Date());
       }
       catch (Exception e){
           return false;
       }
    }
    private Claims extractAllClaims(String token){
        SecretKey secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
        return Jwts
                .parser()
                .decryptWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    private SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }
}
