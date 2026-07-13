package com.compdes.loan_microservice.loan.infrastructure.inputadapters.rest.dto;

import com.compdes.loan_microservice.loan.application.usecases.createloan.CreateLoanDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.time.LocalDate;
import java.util.UUID;

@Value
public class LoanRequestDto {

    @NotBlank
    public String userName;

    @NotNull
    public UUID bookId;

    @NotNull
    public LocalDate endDate;


    public CreateLoanDto toDomain() { // TO APPLICATION
        return new CreateLoanDto(
                this.userName,
                this.bookId,
                this.endDate
        );
    }
}
