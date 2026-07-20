package com.compdes.loan_microservice.loan.infrastructure.outputadapters.persistence.entity.mapper;

import com.compdes.loan_microservice.loan.domain.*;
import com.compdes.loan_microservice.loan.infrastructure.outputadapters.persistence.entity.LoanDbEntity;
import org.springframework.stereotype.Component;

@Component
public class LoanMapper {

    public Loan toDomain(LoanDbEntity loanDbEntity) {
        return new Loan(
                LoanId.fromUUID(loanDbEntity.getId()),
                UserName.fromString(loanDbEntity.getUserName()),
                BookId.fromUUID(loanDbEntity.getBookId()),
                InitDate.fromLocalDate(loanDbEntity.getInitDate()),
                EndDate.fromLocalDate(loanDbEntity.getEndDate()),
                loanDbEntity.getIsActive()
        );
    }

    public LoanDbEntity fromDomain(Loan loan) {
        return new LoanDbEntity(
                loan.getLoanId().getId(),
                loan.getUserName().getUserName(),
                loan.getBookId().getId(),
                loan.getInitDate().getInitDate(),
                loan.getEndDate().getEndDate(),
                loan.getIsActive()
        );
    }
}
