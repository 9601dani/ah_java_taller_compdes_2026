package com.compdes.loan_microservice.loan.application.ouputports.rest;

import java.util.UUID;

public interface UpdatingBookAvailabilityOutputPort {

    boolean updateAvailabilityBook(UUID id);
}
