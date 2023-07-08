package com.example.auth.service;

import com.example.auth.entity.CustomUserDetails;
import com.example.auth.entity.UserEntity;
import com.example.auth.entity.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@Slf4j

// UserDetailsManager의 구현체로 만들면, Spring Security Filter 에서 사용자 정보 회수에 활용할 수 있다.
public class JpaUserDetailsManager implements UserDetailsManager {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public JpaUserDetailsManager(UserRepository userRepository,
                                 PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
//        createUser(User.withUsername("user").password(passwordEncoder.encode("asdf")).build());
        createUser(CustomUserDetails.builder()
                .username("user")
                .password(passwordEncoder.encode("asdf"))
                .email("user@naver.com")
                .phone("111-2222")
                .build());
    }


    @Override
    // UserDetailService.loadUserByUsername(String)
    // 실제로 Spring Security 내부에서 사용하는 반드시 구현해야 정상동작을 기대할 수 있는 메서드
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> optionalUser = userRepository.findByUsername(username);

        if(optionalUser.isEmpty())
            throw new UsernameNotFoundException(username);

        UserEntity userEntity = optionalUser.get();

//        UserDetails newUser = User.withUsername(userEntity.getUsername()).password(userEntity.getPassword()).build();
//        return newUser;

//        return User.withUsername(userEntity.getUsername()).password(userEntity.getPassword()).build();
        return CustomUserDetails.fromEntity(optionalUser.get());
    }

    @Override
    // 새로운 사용자를 저장하는 메서드
    public void createUser(UserDetails user) {
        if(this.userExists(user.getUsername()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

//        UserEntity userEntity = new UserEntity();
//        userEntity.setUsername(user.getUsername());
//        userEntity.setPassword(user.getPassword());
//        this.userRepository.save(userEntity);

        try{
            userRepository.save(((CustomUserDetails) user).newEntity());
        }catch (ClassCastException e) {
            log.error("failed to cast to {}", CustomUserDetails.class);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    // 계정이름을 가진 사용자가 존재하는지 확인하는 메서드
    public boolean userExists(String username) {
        return this.userRepository.existsByUsername(username);
    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

}
