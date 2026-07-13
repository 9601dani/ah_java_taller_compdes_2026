package com.compdes.loan_microservice.loan.infrastructure.inputadapters.rest;

import com.compdes.loan_microservice.common.infrastructure.annotations.WebAdapter;
import com.compdes.loan_microservice.loan.application.inputports.CreatingLoanInputPort;
import com.compdes.loan_microservice.loan.application.usecases.createloan.CreateLoanDto;
import com.compdes.loan_microservice.loan.domain.Loan;
import com.compdes.loan_microservice.loan.infrastructure.inputadapters.rest.dto.LoanRequestDto;
import com.compdes.loan_microservice.loan.infrastructure.inputadapters.rest.dto.LoanResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "loans", description = "Operaciones relacionadas a los préstamos.")
@RequestMapping("/v1/loans")
@RestController
@WebAdapter
public class LoanControllerAdapter {

    private final CreatingLoanInputPort creatingLoanInputPort;

    public LoanControllerAdapter(CreatingLoanInputPort creatingLoanInputPort) {
        this.creatingLoanInputPort = creatingLoanInputPort;
    }


    @Operation(
            summary = "Creación de préstamos",
            description = "Creación de préstamos con nombre de usuario y id de libro."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "préstamo creado."),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado."),
            @ApiResponse(responseCode = "400", description = "préstamo no creado.")
    })
    @PostMapping
    @Transactional
    public ResponseEntity<LoanResponseDto> createLoan(@Valid LoanRequestDto requestDto) {
        CreateLoanDto createLoanDto = requestDto.toDomain();

        Loan loan = this.creatingLoanInputPort.save(createLoanDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(LoanResponseDto.fromDomain(loan));
    }

}
