package com.compdes.loan_microservice.loan.domain;

import lombok.Value;

import java.util.UUID;

@Value
public class LoanId {

    private UUID id;

    public LoanId(UUID id) {
        this.id = id;
    }

    public static LoanId generate() {
        return new LoanId(UUID.randomUUID());
    }

    public static LoanId fromUUID(UUID id) {
        return new LoanId(id);
    }
}
