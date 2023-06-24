package com.example.contents;

import com.example.contents.dto.ResponseDto;
import com.example.contents.exceptions.Status404Exception;
import jdk.jshell.Snippet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice // @ControllerAdvice + @ResponseBody
// 각 Controller 에 나누어진 ExceptionHandler 메소드를 모으는 용도
public class UserControllerAdvice {
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ResponseDto> handleIllegalState(IllegalStateException exception){
        ResponseDto response = new ResponseDto();
        response.setMessage("UserControllerAdvice 에서 처리한 예외입니다.");

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(Status404Exception.class)
    public ResponseEntity<ResponseDto> handle404(Status404Exception exception){
        ResponseDto response = new ResponseDto();
        response.setMessage(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
