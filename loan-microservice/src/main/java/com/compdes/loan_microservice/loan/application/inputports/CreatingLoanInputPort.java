package com.compdes.loan_microservice.loan.application.inputports;

import com.compdes.loan_microservice.common.application.exceptions.EntityNotAvailableException;
import com.compdes.loan_microservice.common.application.exceptions.EntityNotFoundException;
import com.compdes.loan_microservice.loan.application.usecases.createloan.CreateLoanDto;
import com.compdes.loan_microservice.loan.domain.Loan;

public interface CreatingLoanInputPort {

    Loan save(CreateLoanDto dto) throws EntityNotFoundException, EntityNotAvailableException;
}
