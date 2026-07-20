package com.compdes.loan_microservice.loan.domain;

import com.compdes.loan_microservice.common.domain.annotations.DomainEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@DomainEntity
@AllArgsConstructor
@Getter
public class Loan {

    private LoanId loanId;
    private UserName userName;
    private BookId bookId;
    private InitDate initDate;
    private EndDate endDate;
    private Boolean isActive;

    public Loan(Loan loan, Boolean isActive) {
        this.loanId = loan.getLoanId();
        this.userName = loan.getUserName();
        this.bookId = loan.getBookId();
        this.initDate = loan.getInitDate();
        this.endDate = loan.getEndDate();
        this.isActive = isActive;
    }
}
