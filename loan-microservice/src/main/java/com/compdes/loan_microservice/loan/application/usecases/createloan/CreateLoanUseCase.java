package com.compdes.loan_microservice.loan.application.usecases.createloan;

import com.compdes.loan_microservice.common.application.annotations.UseCase;
import com.compdes.loan_microservice.common.application.exceptions.EntityNotAvailableException;
import com.compdes.loan_microservice.common.application.exceptions.EntityNotFoundException;
import com.compdes.loan_microservice.loan.application.inputports.CreatingLoanInputPort;
import com.compdes.loan_microservice.loan.domain.Loan;

@UseCase
public class CreateLoanUseCase implements CreatingLoanInputPort {

    @Override
    public Loan save(CreateLoanDto dto) throws EntityNotFoundException, EntityNotAvailableException {
        Loan loanToCreate = dto.toDomain();
        // TODO: VERIFY USER EXISTS

        // TODO: VERIFY BOOK AVAILABLE

        // TODO: STORE LOAN

        // TODO: UPDATE BOOK AVAILABILITY
        return null;
    }
}
