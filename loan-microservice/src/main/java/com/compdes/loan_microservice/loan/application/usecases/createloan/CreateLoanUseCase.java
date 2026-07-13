package com.compdes.loan_microservice.loan.application.usecases.createloan;

import com.compdes.loan_microservice.common.application.annotations.UseCase;
import com.compdes.loan_microservice.common.application.exceptions.EntityNotAvailableException;
import com.compdes.loan_microservice.common.application.exceptions.EntityNotFoundException;
import com.compdes.loan_microservice.loan.application.inputports.CreatingLoanInputPort;
import com.compdes.loan_microservice.loan.application.ouputports.persistence.StoringLoanOutputPort;
import com.compdes.loan_microservice.loan.application.ouputports.rest.ExistsUserIdOutputPort;
import com.compdes.loan_microservice.loan.application.ouputports.rest.FindingAvailableBookOutputPort;
import com.compdes.loan_microservice.loan.application.ouputports.rest.UpdatingBookAvailabilityOutputPort;
import com.compdes.loan_microservice.loan.domain.Loan;

import java.util.UUID;

@UseCase
public class CreateLoanUseCase implements CreatingLoanInputPort {

    private final FindingAvailableBookOutputPort findingAvailableBookOutputPort;
    private final ExistsUserIdOutputPort existsUserIdOutputPort;
    private final UpdatingBookAvailabilityOutputPort updatingBookAvailabilityOutputPort;
    private final StoringLoanOutputPort storingLoanOutputPort;

    public CreateLoanUseCase(FindingAvailableBookOutputPort findingAvailableBookOutputPort,
                             ExistsUserIdOutputPort existsUserIdOutputPort,
                             StoringLoanOutputPort storingLoanOutputPort,
                             UpdatingBookAvailabilityOutputPort updatingBookAvailabilityOutputPort) {
        this.findingAvailableBookOutputPort = findingAvailableBookOutputPort;
        this.existsUserIdOutputPort = existsUserIdOutputPort;
        this.storingLoanOutputPort = storingLoanOutputPort;
        this.updatingBookAvailabilityOutputPort = updatingBookAvailabilityOutputPort;
    }

    @Override
    public Loan save(CreateLoanDto dto) throws EntityNotFoundException, EntityNotAvailableException {
        String userName = dto.getUserName();
        UUID bookId = dto.getBookId();
        Loan loanToCreate = dto.toDomain();
        // TODO: VERIFY USER EXISTS
        boolean userExists = this.existsUserIdOutputPort.existsUser(userName);

        System.out.println("___________________________________________________");
        System.out.println(userExists);

        if (!userExists)
            throw new EntityNotFoundException("No se encontró el usuario con nombre: " + userName);

        // TODO: VERIFY BOOK AVAILABLE
        boolean isBookAvailable = this.findingAvailableBookOutputPort.findAvailableBook(bookId);

        if (!isBookAvailable)
            throw new EntityNotAvailableException("El libro con id: " + bookId + " no se encuentra disponible.");

        // TODO: UPDATE BOOK AVAILABILITY
        boolean isUpdated = this.updatingBookAvailabilityOutputPort.updateAvailabilityBook(bookId);

        if (!isUpdated)
            throw  new IllegalArgumentException("No se pudo actualizar la disponibilidad del libro.");

        // TODO: STORE LOAN
        try {
            return this.storingLoanOutputPort.save(loanToCreate);
        } catch (Exception e) {
            this.updatingBookAvailabilityOutputPort.updateAvailabilityBook(bookId);
            throw new EntityNotAvailableException("No se pudo crear el préstamo.");
        }
    }
}
