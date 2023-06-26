package com.example.validation.contraints;

import com.example.validation.contraints.annotations.Blacklist;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.HashSet;
import java.util.Set;

public class BlacklistValidator implements ConstraintValidator<Blacklist, String> {

    private Set<String> blacklist;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context){
        return !this.blacklist.contains(value);
    }

    @Override
    public void initialize(Blacklist constraintAnnotation){
        blacklist = new HashSet<>();
        for(String target : constraintAnnotation.blacklist()){
            blacklist.add(target);
        }
    }
}
