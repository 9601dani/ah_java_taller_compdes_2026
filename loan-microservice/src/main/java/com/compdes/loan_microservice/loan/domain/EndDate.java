package com.compdes.loan_microservice.loan.domain;

import lombok.Value;

import java.time.LocalDate;

@Value
public class EndDate {

    private LocalDate endDate;

    public EndDate(LocalDate endDate) {
        if (endDate == null)
            throw new IllegalArgumentException("La fecha de devolución es inválida.");

        if (endDate.isBefore(LocalDate.now().plusDays(1)))
            throw new IllegalArgumentException("La fecha de devolución no puede ser una fecha anterior al día de hoy.");

        this.endDate = endDate;
    }

    public static EndDate fromLocalDate(LocalDate endDate) {
        return new EndDate(endDate);
    }
}
