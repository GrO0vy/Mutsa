package com.example.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class BeerRestService {
    public void getBeerObject(){
        // RestTemplate : Spring 에서 제공하는 기본 HTTP Client
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://random-data-api.com/api/v2/beers";

        // RestTemplate 으로 GET 요청
        // getForObject -> 다른 거 필요없고 응답 바디만 필요할 때 사용
       String responseBody = restTemplate.getForObject(url, String.class);
       BeerGetDto responseBody2 = restTemplate.getForObject(url, BeerGetDto.class);
       log.info(responseBody);
       log.info(responseBody2.toString());
    }

    // STATUS LINE
    // RESPONSE HEADER
    // RESPONSE BODY
    // getForEntity : HTTP 응답 전체 확인
    public void getBeerEntity(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://random-data-api.com/api/v2/beers";

        ResponseEntity<BeerGetDto> response = restTemplate.getForEntity(url, BeerGetDto.class);
        log.info(response.getStatusCode().toString());
        log.info(response.getHeaders().toString());
        log.info(response.getBody().toString());
    }

    // postBeerObject
    public void postBeerObject(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8081/give-me-beer";

        BeerPostDto dto = new BeerPostDto();

        dto.setName("카스");
        dto.setCc(2000l);
        dto.setAlcohol(5.0);

        // post 요청을 보낼 때 requestBody를 같이 전달
        String responseBody = restTemplate.postForObject(url, dto, String.class);
        log.info(responseBody);

        MessageDto responseBody2 = restTemplate.postForObject(url, dto, MessageDto.class);
        log.info(responseBody2.toString());

        // 응답 Body 없이 응답하는 URL
        url = "http://localhost:8081/give-me-beer-204";
        ResponseEntity<Void> response = restTemplate.postForEntity(url, dto, Void.class); // void 의 클래스 ( 객체화 불가 )
        log.info(response.getStatusCode().toString());
    }
}
