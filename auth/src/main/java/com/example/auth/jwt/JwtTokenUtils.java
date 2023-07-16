package com.example.auth.jwt;

import com.example.auth.entity.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
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
    private final Key signingKey;
    // JWT 해석기
    private final JwtParser jwtParser;

    public JwtTokenUtils(@Value("${jwt.secret}") String jwtSecret){
        log.info(jwtSecret);
        this.signingKey = Keys.hmacShaKeyFor(jwtSecret.getBytes());
        this.jwtParser = Jwts
                .parserBuilder()
                .setSigningKey(this.signingKey)
                .build();
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
                .signWith(signingKey)
                .compact();
    }

    // JWT 가 유효한지 판단하는 메소드
    public boolean validate(String token){
        try{
            // JWT 해석기로 토큰을 해석한다.
            jwtParser.parseClaimsJws(token);
            // 해석에 성공하면 유효한 JWT 이므로 true 반환
            return true;
        }catch(Exception e){
            log.warn("invalid jwt: {}",e.getClass());
            // 해석에 실패하면 예외가 일어나고 유효하지 않다는 의미이므로 false 반환
            return false;
        }
    }

    public Claims parseClaims(String token){
        return jwtParser.parseClaimsJws(token).getBody();
    }
}
