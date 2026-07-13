package com.compdes.loan_microservice.loan.infrastructure.outputadapters.persistence;

import com.compdes.loan_microservice.common.application.exceptions.EntityNotAvailableException;
import com.compdes.loan_microservice.common.application.exceptions.EntityNotFoundException;
import com.compdes.loan_microservice.common.infrastructure.annotations.PersistenceAdapter;
import com.compdes.loan_microservice.loan.application.ouputports.persistence.StoringLoanOutputPort;
import com.compdes.loan_microservice.loan.application.usecases.createloan.CreateLoanDto;
import com.compdes.loan_microservice.loan.domain.Loan;
import com.compdes.loan_microservice.loan.infrastructure.outputadapters.persistence.entity.LoanDbEntity;
import com.compdes.loan_microservice.loan.infrastructure.outputadapters.persistence.entity.mapper.LoanMapper;
import com.compdes.loan_microservice.loan.infrastructure.outputadapters.persistence.repository.LoanDbEntityJpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@PersistenceAdapter
public class LoanRepositoryOutputAdapter implements StoringLoanOutputPort {

    private final LoanDbEntityJpaRepository loanDbEntityJpaRepository;
    private final LoanMapper loanMapper;

    public LoanRepositoryOutputAdapter(LoanDbEntityJpaRepository loanDbEntityJpaRepository, LoanMapper loanMapper) {
        this.loanDbEntityJpaRepository = loanDbEntityJpaRepository;
        this.loanMapper = loanMapper;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Loan save(Loan loan) throws EntityNotFoundException, EntityNotAvailableException {
        LoanDbEntity loanToSave = this.loanMapper.fromDomain(loan);
        LoanDbEntity loanSaved = this.loanDbEntityJpaRepository.save(loanToSave);
        return loanMapper.toDomain(loanSaved);
    }
}
