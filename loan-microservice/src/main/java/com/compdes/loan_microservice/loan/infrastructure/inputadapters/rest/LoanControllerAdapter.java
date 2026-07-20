package com.compdes.loan_microservice.loan.infrastructure.inputadapters.rest;

import com.compdes.loan_microservice.common.infrastructure.annotations.WebAdapter;
import com.compdes.loan_microservice.loan.application.inputports.CreatingLoanInputPort;
import com.compdes.loan_microservice.loan.application.inputports.ListingAvailableLoansInputPort;
import com.compdes.loan_microservice.loan.application.inputports.UpdatingLoanInputPort;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "loans", description = "Operaciones relacionadas a los préstamos.")
@RequestMapping("/v1/loans")
@RestController
@WebAdapter
public class LoanControllerAdapter {

    private final CreatingLoanInputPort creatingLoanInputPort;
    private final ListingAvailableLoansInputPort listingAvailableLoansInputPort;
    private final UpdatingLoanInputPort updatingLoanInputPort;

    public LoanControllerAdapter(CreatingLoanInputPort creatingLoanInputPort, ListingAvailableLoansInputPort listingAvailableLoansInputPort, UpdatingLoanInputPort updatingLoanInputPort) {
        this.creatingLoanInputPort = creatingLoanInputPort;
        this.listingAvailableLoansInputPort = listingAvailableLoansInputPort;
        this.updatingLoanInputPort = updatingLoanInputPort;
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

    @Operation(
            summary = "Listado de préstamos activos",
            description = "Devuelve todos los préstamos activos."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "préstamos encontrados."),
            @ApiResponse(responseCode = "404", description = "préstamos no encontrados."),
    })
    @GetMapping
    public ResponseEntity<List<LoanResponseDto>> findAll() {
        List<Loan> loans = this.listingAvailableLoansInputPort.listLoans();
        return ResponseEntity.ok(loans.stream().map(LoanResponseDto::fromDomain).toList());
    }

    @Operation(
            summary = "Devolver libro",
            description = "Devuelve un libro de algún préstamo activo."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Libro devuelto."),
            @ApiResponse(responseCode = "404", description = "Libro no devuelto."),
    })
    @PutMapping("/{bookId}")
    @Transactional
    public ResponseEntity<LoanResponseDto> returnBook(@PathVariable UUID bookId) {
        Loan loan = this.updatingLoanInputPort.updateLoan(bookId);
        return ResponseEntity.ok(LoanResponseDto.fromDomain(loan));
    }

}
