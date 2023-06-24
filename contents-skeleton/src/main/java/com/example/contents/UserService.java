package com.example.contents;

import com.example.contents.dto.UserDto;
import com.example.contents.entity.UserEntity;
import com.example.contents.exceptions.UserNotFoundException;
import com.example.contents.exceptions.UsernameExistException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    // createUsergg
    public UserDto createUser(UserDto dto) {
        if(repository.existsByUsername(dto.getUsername())) {
            throw new UsernameExistException();
            //throw new IllegalStateException();
            //throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(dto.getUsername());
        userEntity.setEmail(dto.getEmail());
        userEntity.setPhone(dto.getPhone());
        userEntity.setBio(dto.getBio());
        userEntity.setAvatar(dto.getAvatar());

        userEntity = repository.save(userEntity);

        return UserDto.fromEntity(userEntity);
    }

    // readUserByUsername
    public UserDto readUserByUsername(String username) {
        if(!repository.existsByUsername(username)) throw new UserNotFoundException();

        Optional<UserEntity> optionalUser = repository.findByUsername(username);

        UserEntity userEntity = optionalUser.get();

        return UserDto.fromEntity(userEntity);
    }

    // updateUser
    public UserDto updateUser(Long id, UserDto dto) {
        if(!repository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        Optional<UserEntity> optionalUser = repository.findById(id);

        UserEntity userEntity = optionalUser.get();
        userEntity.setEmail(dto.getEmail());
        userEntity.setPhone(dto.getPhone());
        userEntity.setBio(dto.getBio());

        userEntity = repository.save(userEntity);

        return UserDto.fromEntity(userEntity);
    }

    // updateUserAvatar
    public UserDto updateUserAvatar(Long id, MultipartFile avatarImage) {
        // 사용자 프로필 이미지 업로드

        // 1. 유저 존재 확인
        Optional<UserEntity> optionalUser = repository.findById(id);

        if(optionalUser.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        String profileDir = String.format("media/%d",id);
        // 2. 파일을 어디에 업로드 할지  media/{userId}/profile.{파일 확장자}
            //폴더 만들기   media/{userId}
        try{
            Files.createDirectories(Path.of(profileDir));
        } catch(IOException e){
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // 확장자를 포함한 이미지 이름 만들기   profile.{파일 확장자}
        String originalFilename = avatarImage.getOriginalFilename();

        String[] fileNameSplit = originalFilename.split("\\.");
        String extension = fileNameSplit[fileNameSplit.length-1];
        String profileFilename = "profile." + extension;

        // 폴더와 파일 경로 포함한 이름 만들기
        String profilePath = String.format("%s/%s",profileDir,profileFilename);

        // MultipartFile 을 저장하기
        try{
            avatarImage.transferTo((Path.of(profilePath)));
        }catch(IOException e){
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // UserEntity 업데이트
        Optional<UserEntity> optionalEntity = repository.findById(id);

        UserEntity userEntity = optionalEntity.get();
        userEntity.setAvatar(String.format("/static/%d/%s",id, profileFilename));

        userEntity = repository.save(userEntity);

        return UserDto.fromEntity(userEntity);
    }
}
