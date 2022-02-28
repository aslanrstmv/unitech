package com.example.unitech.security;

import com.example.unitech.exception.ApiException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    @Value("${app.jwt-secret}")
    private String SECRET_KEY;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 5 * 60 * 60 * 1000))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
//        final String username = extractUsername(token);
//        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
        } catch (SignatureException ex) {
//            throw new ApiException(HttpStatus.BAD_REQUEST, "Invalid JWT signature");
            System.out.println("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
//            throw new ApiException(HttpStatus.BAD_REQUEST, "Invalid JWT token");
            System.out.println("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
//            throw new ApiException(HttpStatus.BAD_REQUEST, "Expired JWT token");
            System.out.println("JWT Token has expired");
        } catch (UnsupportedJwtException ex) {
//            throw new ApiException(HttpStatus.BAD_REQUEST, "Unsupported JWT token");
            System.out.println("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
//            throw new ApiException(HttpStatus.BAD_REQUEST, "JWT claims string is empty");
            System.out.println("JWT claims string is empty");
        }
        return true;
    }
    public Boolean validateToken2(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
        } catch (SignatureException ex) {
            System.out.println("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            System.out.println("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            System.out.println("JWT Token has expired");
        } catch (UnsupportedJwtException ex) {
            System.out.println("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            System.out.println("JWT claims string is empty");
        }
        return true;
    }
}
