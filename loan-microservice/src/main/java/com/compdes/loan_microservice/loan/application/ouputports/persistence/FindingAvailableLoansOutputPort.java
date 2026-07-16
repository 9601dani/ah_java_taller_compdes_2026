package com.compdes.loan_microservice.loan.application.ouputports.persistence;

import com.compdes.loan_microservice.loan.domain.Loan;

import java.util.List;

public interface FindingAvailableLoansOutputPort {

    List<Loan> listAvailableLoans();
}
