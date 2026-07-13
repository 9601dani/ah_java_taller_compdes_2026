package com.compdes.loan_microservice.loan.domain;

import lombok.Value;

import java.time.LocalDate;

@Value
public class InitDate {

    private LocalDate initDate;

    public InitDate(LocalDate initDate) {
        if (initDate == null)
            throw new IllegalArgumentException("La fecha de inicio no es válida.");

        if (initDate.isBefore(LocalDate.now()))
            throw new IllegalArgumentException("La fecha de inicio no puede ser una fecha anterior a la fecha actual.");

        this.initDate = initDate;
    }

    public static InitDate fromLocalDate(LocalDate initDate) {
        return new InitDate(initDate);
    }
}
