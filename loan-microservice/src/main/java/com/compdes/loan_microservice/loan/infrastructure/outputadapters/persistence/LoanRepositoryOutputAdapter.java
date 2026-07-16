package com.compdes.loan_microservice.loan.infrastructure.outputadapters.persistence;

import com.compdes.loan_microservice.common.application.exceptions.EntityNotAvailableException;
import com.compdes.loan_microservice.common.application.exceptions.EntityNotFoundException;
import com.compdes.loan_microservice.common.infrastructure.annotations.PersistenceAdapter;
import com.compdes.loan_microservice.loan.application.inputports.ListingAvailableLoansInputPort;
import com.compdes.loan_microservice.loan.application.ouputports.persistence.FindingAvailableLoanByBookIdOutputPort;
import com.compdes.loan_microservice.loan.application.ouputports.persistence.FindingAvailableLoansOutputPort;
import com.compdes.loan_microservice.loan.application.ouputports.persistence.StoringLoanOutputPort;
import com.compdes.loan_microservice.loan.application.usecases.createloan.CreateLoanDto;
import com.compdes.loan_microservice.loan.domain.Loan;
import com.compdes.loan_microservice.loan.infrastructure.outputadapters.persistence.entity.LoanDbEntity;
import com.compdes.loan_microservice.loan.infrastructure.outputadapters.persistence.entity.mapper.LoanMapper;
import com.compdes.loan_microservice.loan.infrastructure.outputadapters.persistence.repository.LoanDbEntityJpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@PersistenceAdapter
public class LoanRepositoryOutputAdapter implements StoringLoanOutputPort, FindingAvailableLoansOutputPort, FindingAvailableLoanByBookIdOutputPort {

    private final LoanDbEntityJpaRepository loanDbEntityJpaRepository;
    private final LoanMapper loanMapper;

    public LoanRepositoryOutputAdapter(LoanDbEntityJpaRepository loanDbEntityJpaRepository, LoanMapper loanMapper) {
        this.loanDbEntityJpaRepository = loanDbEntityJpaRepository;
        this.loanMapper = loanMapper;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Loan save(Loan loan) {
        LoanDbEntity loanToSave = this.loanMapper.fromDomain(loan);
        LoanDbEntity loanSaved = this.loanDbEntityJpaRepository.save(loanToSave);
        return loanMapper.toDomain(loanSaved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Loan> listAvailableLoans() {
        return this.loanDbEntityJpaRepository.findByIsActive(true)
                .stream()
                .map(this.loanMapper::toDomain)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Loan> findAvailableLoanByBookId(UUID bookId) {
        return this.loanDbEntityJpaRepository.findByIsActiveAndBookId(true, bookId)
                .map(this.loanMapper::toDomain);
    }
}
