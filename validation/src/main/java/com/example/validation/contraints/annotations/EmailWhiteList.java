package com.example.validation.contraints.annotations;

import com.example.validation.contraints.EmailWhiteListValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//@Target(ElementType.PARAMETER)
//@Target(ElementType.METHOD)
//@Target(ElementType.CONSTRUCTOR)
@Target(ElementType.FIELD)   // 어노테이션을 어디에 적용할 것인지 ( 선택 사항 )
@Retention(RetentionPolicy.RUNTIME)     // 실행 중에도 어노테이션이 유지되어야한다. // 롬복 어노테이션의 경우 컴파일 단계가 끝나면 사라진다
@Constraint(validatedBy = EmailWhiteListValidator.class)
public @interface EmailWhiteList {
    //  Annotation Element ( 메서드 x )
    String message() default "email not in whitelist";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
