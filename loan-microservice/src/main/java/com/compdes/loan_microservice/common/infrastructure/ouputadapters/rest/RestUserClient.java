package com.compdes.loan_microservice.common.infrastructure.ouputadapters.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestUserClient {

    @Value("${USER_CLIENT_URI:http://localhost:8002}")
    private String userClientUri;

    @Bean("RestUserClient")
    public RestClient restUserClient() {
        return RestClient.builder()
                .baseUrl(userClientUri + "/v1/users/check/")
                .build();
    }
}
