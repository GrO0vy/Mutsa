package com.example.validation.contraints;

import com.example.validation.contraints.annotations.EmailWhiteList;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.HashSet;
import java.util.Set;

//  데이터 유효성 검사기
public class EmailWhiteListValidator implements ConstraintValidator<EmailWhiteList, String> {
    private final Set<String> whiteList;

    public EmailWhiteListValidator(){
        this.whiteList = new HashSet<>();
        this.whiteList.add("gmail.com");
        this.whiteList.add("naver.com");
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context){
        //  유효한 값일 때 true 를 반환한다.
        //  유효하지 않은 값일 때 false 를 반환

        String[] split = value.split("@");
        String domain = split[split.length-1];

        return whiteList.contains(domain);
    }
}
