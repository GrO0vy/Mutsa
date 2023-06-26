package com.example.validation.contraints.annotations;

import com.example.validation.contraints.Phone010Validator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = Phone010Validator.class)
public @interface Phone010 {
    String message() default "phonenumber not start from 010";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
