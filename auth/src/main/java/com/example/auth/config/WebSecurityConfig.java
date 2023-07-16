package com.example.auth.config;

import com.example.auth.jwt.JwtTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

// 5.7 이전 버전
// 6.1 버전
@Configuration
// @EnableWebSecurity -> 2.1 버전 이후에서는 필수 아니다.
public class WebSecurityConfig {
    private final JwtTokenFilter jwtTokenFilter;

    public WebSecurityConfig(JwtTokenFilter jwtTokenFilter) {
        this.jwtTokenFilter = jwtTokenFilter;
    }

    @Bean // 메서드의 결과를 BEAN 객체로 등록해주는 어노테이션
    public SecurityFilterChain securityFilterChain(
            // DI 자동으로 설정됨, 빌더 패턴처럼 사용
            HttpSecurity http
    ) throws Exception
    {
        // requestMatchers == 어떤 URL로 오는 요청에 대하여 설정하는지
        // permitAll() == 누가 요청해도 허가한다.
        // authenticated() == 인증이 된 사용자만 허가
        // anonymous() == 인증되지 않은 사용자만 허가
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authHttp -> authHttp
                                .requestMatchers("/no-auth",
                                        "/token/issue")
                                .permitAll()
//                                .requestMatchers("/",
//                                        "/users/register")
//                                .anonymous()
                                .anyRequest()
                                .authenticated()
                ).sessionManagement(
                        sessionManagement -> sessionManagement
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(jwtTokenFilter, AuthorizationFilter.class);
                // HTTP 요청 허가 관련 설정
        // form 을 이용한 로그인 관련 설정
//                .formLogin(
//                        formLogin -> formLogin
//                                // 로그인 하는 페이지 ( 경로 ) 를 지정
//                                .loginPage("/users/login")
//                                // 로그인 성공 시 이동하는 페이지 ( 경로 )
//                                .defaultSuccessUrl("/users/my-profile")
//                                // 로그인 실패 시 이동하는 페이지
//                                .failureUrl("/users/login/fail")
//                                // 로그인 과정에서 필요한 경로 ( 요청 ) 들을 모두가 사용할 수 있게 권한 설정
//                                .permitAll()
//                )
//                // 로그아웃 관련 설정
//                .logout(
//                        logout -> logout
//                                // 로그아웃 요청을 보낼 URL ( Post 요청 )
//                                .logoutUrl("/users/logout")
//                                // 로그아웃 성공 시 URL 설정
//                                .logoutSuccessUrl("/users/login")
//                );
        return http.build();
    }

    //@Bean
    // 사용자 관리를 위한 인터페이스 구현체 Bean
    public UserDetailsManager userDetailsManager(
        PasswordEncoder passwordEncoder
    )
    {
        UserDetails user1 = User.withUsername("user1")
                .password(passwordEncoder.encode("password"))
                .build();
        // Spring 에서 미리 만들어 놓은 사용자 인증 서비스
        return new InMemoryUserDetailsManager(user1);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        // 사용자 비밀번호는 해독가능한 형태로 데이터베이스에 저장되면 안된다.
        // 기본적으로 비빌번호를 단방향 암호화 하는 인코더를 사용한다.
        return new BCryptPasswordEncoder();
    }
}
