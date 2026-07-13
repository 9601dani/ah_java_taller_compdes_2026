package com.compdes.loan_microservice.common.infrastructure.ouputadapters.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestBookClient {

    @Value("${BOOK_CLIENT_URI:http://localhost:8001}")
    private String bookClientUri;

    @Bean("RestBookClient")
    public RestClient restBookClient() {
        return RestClient.builder()
                .baseUrl(bookClientUri + "/v1/books/availability")
                .build();
    }
}
