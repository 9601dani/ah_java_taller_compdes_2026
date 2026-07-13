package com.compdes.loan_microservice.loan.domain;

import lombok.Value;

import java.util.UUID;

@Value
public class BookId {

    private UUID id;

    public BookId(UUID id) {
        if (id == null)
            throw new IllegalArgumentException("El id del libro no puede estar vacío.");

        this.id = id;
    }

    public static BookId fromUUID(UUID id) {
        return new BookId(id);
    }
}
