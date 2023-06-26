package com.example.validation.dto;

import com.example.validation.contraints.annotations.Blacklist;
import com.example.validation.contraints.annotations.EmailWhiteList;
import com.example.validation.contraints.annotations.Phone010;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UserDto {
    private Long id;

    @NotBlank   // 비어있지 않다.
    @Size(min = 8)  //  크기의 최솟값을 8로한다.
    @Blacklist(blacklist = {"Hackersss","Bugggsss"})
    private String username;
    @Email  // 형식이 이메일이어야한다.
    @EmailWhiteList
    private String email;
    @NotNull    // 비어있지 않다.
    @Phone010
    private String phone;

    @NotNull
    @Min(value = 14, message = "14세 미만은 부모님의 동의가 필요합니다.")    // 최솟값 14
    private Integer age;

    @Future // 미래의 시간만
    private LocalDate validUntil;

    @NotNull    // null 이 아닌지를 검증
    private String notNullString;
    @NotEmpty   // 길이가 0이 아닌지를 검사
    private String notEmptyString;
    @NotBlank   // 공백문자로만 이루어지지 않았는지를 검사
    private String notBlankString;
}

/*
{
    "username" : "something",
    "email" : "something",
    "phone" : "something"
}
 */
