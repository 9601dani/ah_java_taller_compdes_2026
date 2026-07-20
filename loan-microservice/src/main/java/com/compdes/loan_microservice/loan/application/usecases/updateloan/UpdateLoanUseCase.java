package com.compdes.loan_microservice.loan.application.usecases.updateloan;

import com.compdes.loan_microservice.common.application.annotations.UseCase;
import com.compdes.loan_microservice.common.application.exceptions.EntityNotFoundException;
import com.compdes.loan_microservice.loan.application.inputports.UpdatingLoanInputPort;
import com.compdes.loan_microservice.loan.application.ouputports.persistence.FindingAvailableLoanByBookIdOutputPort;
import com.compdes.loan_microservice.loan.application.ouputports.persistence.StoringLoanOutputPort;
import com.compdes.loan_microservice.loan.application.ouputports.rest.UpdatingBookAvailabilityOutputPort;
import com.compdes.loan_microservice.loan.domain.Loan;

import java.util.Optional;
import java.util.UUID;

@UseCase
public class UpdateLoanUseCase implements UpdatingLoanInputPort {

    private final FindingAvailableLoanByBookIdOutputPort findingAvailableLoanByBookIdOutputPort;
    private final StoringLoanOutputPort storingLoanOutputPort;
    private final UpdatingBookAvailabilityOutputPort updatingBookAvailabilityOutputPort;

    public UpdateLoanUseCase(FindingAvailableLoanByBookIdOutputPort findingAvailableLoanByBookIdOutputPort,
                             StoringLoanOutputPort storingLoanOutputPort,
                             UpdatingBookAvailabilityOutputPort updatingBookAvailabilityOutputPort) {
        this.findingAvailableLoanByBookIdOutputPort = findingAvailableLoanByBookIdOutputPort;
        this.storingLoanOutputPort = storingLoanOutputPort;
        this.updatingBookAvailabilityOutputPort = updatingBookAvailabilityOutputPort;
    }

    @Override
    public Loan updateLoan(UUID bookId) throws EntityNotFoundException {
        Optional<Loan> optionalLoan = this.findingAvailableLoanByBookIdOutputPort.findAvailableLoanByBookId(bookId);

        if (optionalLoan.isEmpty())
            throw new EntityNotFoundException("No se encontró un préstamo activo para el libro: " + bookId);

        Loan activeLoan = optionalLoan.get();

        // TODO: CONSTRUCTOR ACCEPT LOAN AND IS ACTIVE
        Loan loanUpdated = new Loan(
                activeLoan,
                false
        );

        boolean isAvailabilityUpdated = this.updatingBookAvailabilityOutputPort.updateAvailabilityBook(bookId);

        if (!isAvailabilityUpdated)
            throw new IllegalArgumentException("No se pudo actualizar el estado del libro: " + bookId);

        try {
            return this.storingLoanOutputPort.save(loanUpdated);
        } catch (Exception e) {
            this.updatingBookAvailabilityOutputPort.updateAvailabilityBook(bookId);
            throw new IllegalArgumentException("Error al actualizar el estado del préstamo.");
        }
    }
}
