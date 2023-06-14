package com.example.jpa;

import org.springframework.stereotype.Component;

@Component
public class AppComponent {
    // Controller, Service, Repository 가 아닌 평범한 Bean 객체
    // 외부 API 사용, 공유된 기능 개발, IOC 에 그냥 등록하고 싶은 객체를 @Component로 사용
}
