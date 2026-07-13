package com.compdes.loan_microservice.loan.application.usecases.createloan;

import com.compdes.loan_microservice.loan.domain.*;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.time.LocalDate;
import java.util.UUID;

@Value
@AllArgsConstructor
public class CreateLoanDto {

    private String userName;
    private UUID bookId;
    private LocalDate initDate;
    private LocalDate endDate;

    public Loan toDomain() {
        return new Loan(
                LoanId.generate(),
                UserName.fromString(userName),
                BookId.fromUUID(bookId),
                InitDate.fromLocalDate(initDate),
                EndDate.fromLocalDate(endDate),
                true
        );
    }
}
