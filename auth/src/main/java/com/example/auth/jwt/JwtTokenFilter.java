package com.example.auth.jwt;

import com.example.auth.entity.CustomUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Filter;

@Slf4j
@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    private final JwtTokenUtils jwtTokenUtils;

    public JwtTokenFilter(JwtTokenUtils jwtTokenUtils){
        this.jwtTokenUtils = jwtTokenUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain
    ) throws ServletException, IOException
    {
        // JWT 가 포함되어 있으면 포함되어 있는 헤더를 요청한다.
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        // authHeader 가 NULL 이 아니면서 "Bearer " 로 구성되어 있어야 정상적인 인증 정보
        if(authHeader != null && authHeader.startsWith("Bearer ")){
            String token = authHeader.split(" ")[1];

            if(jwtTokenUtils.validate(token)){
                SecurityContext context = SecurityContextHolder.createEmptyContext();

                String username = jwtTokenUtils.parseClaims(token).getSubject();

                AbstractAuthenticationToken abstractAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(
                                CustomUserDetails.builder()
                                        .username(username)
                                        .build(), token, new ArrayList<>());

                context.setAuthentication(abstractAuthenticationToken);

                SecurityContextHolder.setContext(context);
            }
            else{
                log.warn("Error!");
            }
        }


        // 다음 필터를 실행할 수 있도록하는 메서드, 이걸 안하면 다른 기본 필터들이 동작 안 할 수도 있다.
        filterChain.doFilter(request, response);
    }


}
