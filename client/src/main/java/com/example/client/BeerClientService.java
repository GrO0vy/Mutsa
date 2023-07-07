package com.example.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
public class BeerClientService {
    public void getBeer(){
        // WebClient는 인터페이스, 구현 클래스가 없고 Builder 를 제공해서 builder 를 사용
        WebClient webClient = WebClient.builder().build();

        String url = "https://random-data-api.com/api/v2/beers?size=10";

        // 어떤 HTTP 메소드로 요청을 보낼지를 get() 또는 post() 를 사용해서 결정한다.
        // 다른 메소드를 사용해서 요청을 보내고 싶다면 method() 사용

        BeerGetDto[] response = webClient.get() // webClient.method(HttpMethod.GET)
                .uri(url)
                .header("x-test","header")
                .retrieve() // 여기 까지 요청을 정의함을 의미
                // 여기부터는 응답을 어떻게 처리할지
                .bodyToMono(BeerGetDto[].class) // 응답이 한 번 돌아올 것이고 그 응답의 body 를 BeerGetDto 형으로 해석
                .block(); // 동기식으로 처리하겠다.

        log.info(response.toString());

    }


    public void postBeer(){
        WebClient webClient = WebClient.builder().build();

        String url = "http://localhost:8081/give-me-beer";

        BeerPostDto dto = new BeerPostDto();
        dto.setName("Cass");
        dto.setCc(550l);
        dto.setAlcohol(5.4);

        MessageDto responseBody = webClient.post()
                .uri(url)
                .bodyValue(dto)
                .retrieve()
                .bodyToMono(MessageDto.class)
                .block();

        log.info(responseBody.toString());
    }

    public void postBeer204(){
        WebClient webClient = WebClient.builder().build();

        String url = "http://localhost:8081/give-me-beer-204";

        BeerPostDto dto = new BeerPostDto();
        ResponseEntity<Void> response = webClient.post()
                .uri(url)
                .bodyValue(dto)
                .retrieve()
                .toBodilessEntity() // 응답 body 가 없을 때 사용
                .block();

        log.info(response.getStatusCode().toString());
    }
}
