package com.springboot.gym.gymsystem.auth.jwt;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private static final String SECRET_KEY = "77G4568E62535625J5632F654332A56465831M156J8472B4B6250";


    public String getToken(UserDetails userDetails){
        return Jwts.builder()
        .claims(Map.of("role", userDetails.getAuthorities().stream()
        .findFirst()
        .map(grantedAuthority -> "ROLE_" + grantedAuthority.getAuthority())
        .orElseThrow(()-> new IllegalStateException("El usuario no tienen ningun Role"))))
        .subject(userDetails.getUsername())
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 3 ))
        .signWith(getKey())
        .compact();
    }


    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    public String getUserNameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
            }
        
        
     private <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaims(token);
          return claimsResolver.apply(claims);                
           }
                
                
    private Claims getAllClaims(String token) {
       return Jwts
       .parser()
       .verifyWith((SecretKey) getKey())
       .build()
       .parseSignedClaims(token)
       .getPayload();                       
            }


    public boolean isTokenValid(String token, UserDetails userDetails) {
        throw new UnsupportedOperationException("Unimplemented method 'isTokenValid'");
    }


    
}
