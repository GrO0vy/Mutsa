package com.example.client.client;

import com.example.client.BeerGetDto;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@Primary
public class BeerWebClient implements BeerClient{
    public BeerGetDto getBeer(){
        WebClient webClient = WebClient.builder().build();
        String url = "https://random-data-api.com/api/v2/beers";

        BeerGetDto response = webClient.get().uri(url).retrieve().bodyToMono(BeerGetDto.class).block();

        return response;
    }
}
