package ru.tellurian.fin_lit_api.repository.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;
import ru.tellurian.fin_lit_api.model.entity.User;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;


@Getter
@Repository
public class JwtTokenRepository {

    @Value("${auth.token.secret}")
    private String secret;

    @Value("${auth.token.lifetime}")
    private int lifetime;


    public String generateToken(Authentication authentication) {
        User user =  new User((org.springframework.security.core.userdetails.User)authentication.getPrincipal());
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .subject(user.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + lifetime))
                .signWith(key)
                .compact();
    }

    public String getNameFromJwtToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject();
    }
}
