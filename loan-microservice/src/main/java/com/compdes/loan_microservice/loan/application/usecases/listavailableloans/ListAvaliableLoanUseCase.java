package com.compdes.loan_microservice.loan.application.usecases.listavailableloans;

import com.compdes.loan_microservice.common.application.annotations.UseCase;
import com.compdes.loan_microservice.loan.application.inputports.ListingAvailableLoansInputPort;
import com.compdes.loan_microservice.loan.application.ouputports.persistence.FindingAvailableLoansOutputPort;
import com.compdes.loan_microservice.loan.domain.Loan;

import java.util.List;

@UseCase
public class ListAvaliableLoanUseCase implements ListingAvailableLoansInputPort {

    private final FindingAvailableLoansOutputPort findingAvailableLoansOutputPort;

    public ListAvaliableLoanUseCase(FindingAvailableLoansOutputPort findingAvailableLoansOutputPort) {
        this.findingAvailableLoansOutputPort = findingAvailableLoansOutputPort;
    }

    @Override
    public List<Loan> listLoans() {
        return this.findingAvailableLoansOutputPort.listAvailableLoans();
    }
}
