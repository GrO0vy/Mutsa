package com.example.aop.controller;

import com.example.aop.aspect.LogArguments;
import com.example.aop.aspect.LogExecutionTime;
import com.example.aop.dto.ResponseDto;
import com.example.aop.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AopController {
    @PostMapping("/users")
    //  컨트롤러의 코드를 크게 바꾸지 않으면서
    //  부수적인 기능 추가
    @LogArguments
    @LogExecutionTime
    public ResponseDto addUser(@RequestBody UserDto user){
        log.info(user.toString());
        ResponseDto response = new ResponseDto();
        response.setMessage("success!");

        return response;
    }

    @GetMapping("/users")
    @LogArguments
    @LogExecutionTime
    public ResponseDto getUsers(){
        ResponseDto response = new ResponseDto();
        response.setMessage("success!");

        return response;
    }

    // 어떤 메서드가 실행되는데 걸리는 시간 기록

}
