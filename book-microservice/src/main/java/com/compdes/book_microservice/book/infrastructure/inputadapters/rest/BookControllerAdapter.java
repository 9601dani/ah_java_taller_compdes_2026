package com.compdes.book_microservice.book.infrastructure.inputadapters.rest;

import com.compdes.book_microservice.book.application.inputports.CreatingBookInputPort;
import com.compdes.book_microservice.book.application.usecases.createbook.CreateBookDto;
import com.compdes.book_microservice.book.domain.Book;
import com.compdes.book_microservice.book.infrastructure.inputadapters.rest.dto.CreateBookRequestDto;
import com.compdes.book_microservice.book.infrastructure.inputadapters.rest.dto.CreateBookResponseDto;
import com.compdes.book_microservice.common.infrastructure.annotations.WebAdapter;
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

@Tag(name = "books", description = "Operaciones relacionadas a los libros.")
@RestController
@RequestMapping("/v1/books")
@WebAdapter
public class BookControllerAdapter {

    private final CreatingBookInputPort creatingBookInputPort;

    public BookControllerAdapter(CreatingBookInputPort creatingBookInputPort) {
        this.creatingBookInputPort = creatingBookInputPort;
    }

    @Operation(
            summary = "Registrar nueva categoria",
            description = "Devuelve la información de la categoria correspondiente."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoria creado"),
            @ApiResponse(responseCode = "404", description = "Categoria no creado")
    })
    @PostMapping
    @Transactional
    public ResponseEntity<CreateBookResponseDto> createBook(@Valid CreateBookRequestDto dto) {
        CreateBookDto createBookDto = dto.toDomain();
        Book book = this.creatingBookInputPort.save(createBookDto);
        CreateBookResponseDto response = CreateBookResponseDto.fromDomain(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
