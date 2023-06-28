package com.example.aop.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j  //  log 추가
@Aspect //  이 클래스가 관점임을 드러내는 어노테이션
@Component  //  Bean 객체로 등록
public class LoggingAspect {
    //  @Before("this(com.example.aop.controller.AopController)")   // @Before : Advice - 실제 실행될 코드를 나타냄
    //  @Before.value : Pointcut Designator, 어느 JoinPoint 에서 실행될것인지
    //  JoinPoint : 이 Advice 가 실행된 JoinPoint ( addUser )
    //  execution : 특정 매소드에 대해서 Aspect 를 실행하겠다
    //  within : 특정 클래스나 패키지에 대해 Aspect 를 실행
    //  @Before("within(com.example.aop.controller.AopController)")
    //  @Before("within(com.example.aop.controller..*)")
    //  @annotation : 특정 어노테이션에 대해 Aspect 를 실행
    @Before("@annotation(com.example.aop.aspect.LogArguments)")
    public void logParameters(JoinPoint joinPoint){
//        log.info("hello aop!");

        // Signature : JoinPoint 의 정보를 담은 객체
        Signature methodSignature = joinPoint.getSignature();

        //  메소드 이름 로그
        log.info(methodSignature.getName());
        Object[] arguments = joinPoint.getArgs();

        //  인자가 없을 때
        if(arguments.length == 0){
            log.info("no args");
        }
        for(Object argument: arguments){
            log.info("argument: [{}]", argument);
        }
    }

    @Around("@annotation(com.example.aop.aspect.LogExecutionTime)")
    public Object logExecutionTime(
            //  Advice 내에서 대상 JoinPoint 가 실행되도록 요구 가능
            ProceedingJoinPoint joinPoint
    ) throws Throwable{
        long startTime = System.currentTimeMillis();

        // JoinPoint 에 해당하는 메서드 진행
        Object proceed = joinPoint.proceed();

        long endTime = System.currentTimeMillis();
        log.info("{} executed in: {}ms",joinPoint.getSignature(),endTime-startTime);

        return proceed;
    }
}
