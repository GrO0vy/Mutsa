package com.example.http;

import com.example.http.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
//@Controller
@RestController
public class BodyController {
    // `/body`로 요청이 들어왔을 때, ResponseDto 데이터를 표현한 JSON 응답을 반환하는 메서드
    @PostMapping("/body")
    // HTTP 응답의 Body임을 나타내는 어노테이션
    public ResponseDto body(@RequestBody ArticleDto requestDto){

        log.info(requestDto.toString());
        ResponseDto response = new ResponseDto();
        response.setStatus(200);
        response.setMessage("success");
        return response;
    }

//    @PostMapping("/body")
//    public @ResponseBody ResponseDto body(@RequestBody ArticleDto request){
//        log.info(request.toString());
//
//        ResponseDto response = new ResponseDto();
//        response.setStatus(202);
//        response.setMessage("작성 성공!");
//        return response;
//    }

    @PostMapping("/body-2")
    public ResponseDto body2(@RequestBody UserDto request){
        log.info("POST /body-2 " + request.toString());
        ResponseDto response = new ResponseDto();
        response.setMessage("success");
        response.setStatus(200);

        return response;
    }

    @PostMapping("/body-3")
    public ResponseDto body3(@RequestBody ArticleWithComment request){
        log.info("POST /body-3 " + request.toString());

        ResponseDto response = new ResponseDto();
        response.setStatus(200);
        response.setMessage("success");

        return response;
    }

    @PostMapping("/body-4")
    //@ResponseBody
    public ResponseDto body4(@RequestBody ArticleComplexDto request){
        log.info("POST /body-4 " + request.toString());

        ResponseDto response = new ResponseDto();
        response.setStatus(200);
        response.setMessage("success");

        return response;
    }

    // @ResponseBody는 요청의 HTTP body 만 설정한다.
    // Header를 추가하거나 Status Code를 고르고 싶을 때 사용
    // ResponseEntity<T>
    @PostMapping("/entity")
    public ResponseEntity<ResponseDto> entity(@RequestBody ArticleDto requestDto){
        log.info(requestDto.toString());
        ResponseDto response = new ResponseDto();
        response.setStatus(200);
        response.setMessage("success");

        // ResponseEntity 객체 그냥 쓰기
//        ResponseEntity<ResponseDto> responseEntity = new ResponseEntity<>(response, HttpStatus.CREATED);
//         return responseEntity;

        // 커스텀 헤더 만들고 함께 응답하기
        HttpHeaders headers = new HttpHeaders();
        headers.add("x-likelion-custom","Hello World!");
//        return new ResponseEntity<>(response,headers,HttpStatus.ACCEPTED);

        // Builder 사용하기
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("x-likelion-one","1")
                .headers(headers)
                .body(response);
    }


}
