package com.compdes.loan_microservice.loan.application.ouputports.persistence;

import com.compdes.loan_microservice.loan.domain.Loan;

public interface StoringLoanOutputPort {

    Loan save(Loan loan);

}
