package com.compdes.loan_microservice.loan.infrastructure.outputadapters.rest;

import com.compdes.loan_microservice.loan.application.ouputports.rest.UpdatingBookAvailabilityOutputPort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

import java.util.UUID;

@Component
public class UpdateBookAvailabilityRestApiOutputAdapter implements UpdatingBookAvailabilityOutputPort {

    private final RestClient restUpdateBookClient;

    public UpdateBookAvailabilityRestApiOutputAdapter(@Qualifier("RestUpdateBookClient") RestClient restUpdateBookClient) {
        this.restUpdateBookClient = restUpdateBookClient;
    }

    @Override
    public boolean updateAvailabilityBook(UUID id) {
        try {
            this.restUpdateBookClient.put()
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
