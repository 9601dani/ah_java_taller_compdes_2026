package com.compdes.loan_microservice.loan.infrastructure.outputadapters.rest;

import com.compdes.loan_microservice.loan.application.ouputports.rest.FindingAvailableBookOutputPort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

import java.util.UUID;

@Component
public class CheckBookRestApiOutputAdapter implements FindingAvailableBookOutputPort {

    private final RestClient restCheckBookClient;

    public CheckBookRestApiOutputAdapter(@Qualifier("RestCheckBookClient") RestClient restCheckBookClient) {
        this.restCheckBookClient = restCheckBookClient;
    }

    @Override
    public boolean findAvailableBook(UUID id) {
        try {
            this.restCheckBookClient.head()
                    .uri(id.toString())
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
