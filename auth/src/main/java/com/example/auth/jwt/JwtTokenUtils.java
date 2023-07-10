package com.example.auth.jwt;

import com.example.auth.entity.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Instant;
import java.util.Date;

// JWT 관련 기능들을 넣어두기 위한 기능성 클래스
@Slf4j
@Component
public class JwtTokenUtils {
    private final Key signinKey;

    public JwtTokenUtils(@Value("${jwt.secret}") String jwtSecret){
        log.info(jwtSecret);
        this.signinKey = Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    // 주어진 사용자 정보를 바탕으로 JWT를 문자열로 생성
    public String generateToken(UserDetails userDetails){
        // Claims: JWT 에 담기는 정보의 단위
        //          Claims 는 Claim 들을 담기위한 Map 의 상속 인터페이스
        Claims jwtClaims = Jwts.claims()
                // 사용자 정보 등록
                .setSubject(userDetails.getUsername())
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plusSeconds(3600)));

        jwtClaims.put("eml",((CustomUserDetails)userDetails).getEmail());

        return Jwts.builder()
                .setClaims(jwtClaims)
                .signWith(signinKey)
                .compact();
    }
}
