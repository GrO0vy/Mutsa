package com.example.auth;

import com.example.auth.jwt.JwtRequestDto;
import com.example.auth.jwt.JwtTokenDto;
import com.example.auth.jwt.JwtTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController
@RequestMapping("token")
public class TokenController {
    // UserDetailsManager
    // PasswordEncoder

    private final UserDetailsManager manager;
    private final PasswordEncoder encoder;
    private final JwtTokenUtils jwtTokenUtils;
    public TokenController(UserDetailsManager manager, PasswordEncoder encoder, JwtTokenUtils jwtTokenUtils){
        this.manager = manager;
        this.encoder = encoder;
        this.jwtTokenUtils = jwtTokenUtils;
    }

    // JWT 발급용 endpoint//
    @PostMapping("/issue")
    public JwtTokenDto issueJwt(@RequestBody JwtRequestDto dto){
        // 사용자 정보 회수
        UserDetails userDetails = manager.loadUserByUsername(dto.getUsername());

        // 기록된 비밀번호와 실제 비밀번호가 다를때
        if(!encoder.matches(dto.getPassword(), userDetails.getPassword()))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);

        JwtTokenDto response = new JwtTokenDto();
        response.setToken(jwtTokenUtils.generateToken(userDetails));
        return response;
    }

}
