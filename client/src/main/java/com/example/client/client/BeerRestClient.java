package com.example.client.client;

import com.example.client.BeerGetDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

// 그냥 평범한 Bean 객체
@Component("BeerRestClient")
public class BeerRestClient implements BeerClient {
    public BeerGetDto getBeer(){
        // BeerRestService 에 있는 코드를 가져오면 된다.
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://random-data-api.com/api/v2/beers";

        return restTemplate.getForObject(url, BeerGetDto.class);
    }
}
