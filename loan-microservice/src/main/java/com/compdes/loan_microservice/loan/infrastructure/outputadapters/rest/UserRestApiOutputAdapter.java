package com.compdes.loan_microservice.loan.infrastructure.outputadapters.rest;

import com.compdes.loan_microservice.loan.application.ouputports.rest.ExistsUserIdOutputPort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

@Component
public class UserRestApiOutputAdapter implements ExistsUserIdOutputPort {

    private final RestClient restUserClient;

    public UserRestApiOutputAdapter(@Qualifier("RestUserClient") RestClient restUserClient) {
        this.restUserClient = restUserClient;
    }

    @Override
    @Transactional
    public boolean existsUser(String username) {
        String normalizedUsername = username;
        try {
            this.restUserClient.head()
                    .uri(normalizedUsername)
                    .retrieve()
                    .toBodilessEntity();
            return true;
        } catch (RestClientResponseException e) {
            if (e.getStatusCode().isSameCodeAs(HttpStatus.NOT_FOUND)) return false;
            e.printStackTrace();
        }

        return false;
    }
}
