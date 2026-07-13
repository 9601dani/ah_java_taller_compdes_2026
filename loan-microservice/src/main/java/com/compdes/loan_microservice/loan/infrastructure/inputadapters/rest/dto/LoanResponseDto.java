package com.compdes.loan_microservice.loan.infrastructure.inputadapters.rest.dto;

import com.compdes.loan_microservice.loan.domain.Loan;
import lombok.Value;

import java.time.LocalDate;
import java.util.UUID;

@Value
public class LoanResponseDto {

    public UUID loanId;
    public String userName;
    public UUID bookId;
    public LocalDate initDate;
    public LocalDate endDate;
    public Boolean isActive;

    public static LoanResponseDto fromDomain(Loan loan) {
        return new LoanResponseDto(
                loan.getLoanId().getId(),
                loan.getUserName().getUserName(),
                loan.getBookId().getId(),
                loan.getInitDate().getInitDate(),
                loan.getEndDate().getEndDate(),
                loan.getIsActive()
        );
    }
}
