package com.compdes.loan_microservice.loan.application.inputports;

import com.compdes.loan_microservice.common.application.exceptions.EntityNotFoundException;
import com.compdes.loan_microservice.loan.domain.Loan;

import java.util.UUID;

public interface UpdatingLoanInputPort {

    Loan updateLoan(UUID bookId) throws EntityNotFoundException;
}
