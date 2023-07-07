package com.example.client.service;

import com.example.client.BeerGetDto;
import com.example.client.client.BeerClient;
import com.example.client.client.BeerRestClient;
import com.example.client.client.BeerWebClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BeerService {
//    private final BeerRestClient beerRestClient;
//    private final BeerWebClient beerWebClient;
    private final BeerClient client;

    public BeerService(
            // 인터페이스가 두 개이상의 구현체를 가질 때는 어떤 구현체를 우선적으로 주입 받을지를 결정해야한다.
            // @Qulifier(컴포넌트명) 또는 우선적으로 주입 받을 구현체에 @Primary 를 붙임

            //@Qualifier("BeerRestClient")
            BeerClient client
    ){
        this.client = client;
    }

    public void drinkBeer(){
        log.info("order beer");
        // TODO API를 활용해 맥주 정보 받아오기
        //BeerGetDto result = beerRestClient.getBeer();
        BeerGetDto result = client.getBeer();
        // 맥주 정보를 받아오는 방법은 비즈니스 로직에서 벗어나지 않을까?
        log.info("{}는 맛있다", result.getName());
    }
}
