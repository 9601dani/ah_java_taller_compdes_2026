package com.compdes.loan_microservice.loan.application.ouputports.persistence;

import com.compdes.loan_microservice.loan.domain.Loan;

import java.util.Optional;
import java.util.UUID;

public interface FindingAvailableLoanByBookIdOutputPort {

    Optional<Loan> findAvailableLoanByBookId(UUID bookId);
}
