package com.compdes.book_microservice.book.infrastructure.inputadapters.rest;

import com.compdes.book_microservice.book.application.inputports.*;
import com.compdes.book_microservice.book.application.usecases.createbook.CreateBookDto;
import com.compdes.book_microservice.book.application.usecases.updatebook.UpdateBookDto;
import com.compdes.book_microservice.book.domain.Book;
import com.compdes.book_microservice.book.infrastructure.inputadapters.rest.dto.BookRequestDto;
import com.compdes.book_microservice.book.infrastructure.inputadapters.rest.dto.BookResponseDto;
import com.compdes.book_microservice.common.infrastructure.annotations.WebAdapter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Tag(name = "books", description = "Operaciones relacionadas a los libros.")
@RestController
@RequestMapping("/v1/books")
@WebAdapter
public class BookControllerAdapter {

    private final CreatingBookInputPort creatingBookInputPort;
    private final ListingAllBooksInputPort listingAllBooksInputPort;
    private final UpdatingBookInputPort updatingBookInputPort;
    private final FindingAvailableBookInputPort findingAvailableBookInputPort;
    private final UpdatingBookAvailabilityInputPort updatingBookAvailabilityInputPort;

    public BookControllerAdapter(CreatingBookInputPort creatingBookInputPort,
                                 ListingAllBooksInputPort listingAllBooksInputPort,
                                 UpdatingBookInputPort updatingBookInputPort,
                                 FindingAvailableBookInputPort findingAvailableBookInputPort,
                                 UpdatingBookAvailabilityInputPort updatingBookAvailabilityInputPort) {
        this.creatingBookInputPort = creatingBookInputPort;
        this.listingAllBooksInputPort = listingAllBooksInputPort;
        this.updatingBookInputPort = updatingBookInputPort;
        this.findingAvailableBookInputPort = findingAvailableBookInputPort;
        this.updatingBookAvailabilityInputPort = updatingBookAvailabilityInputPort;
    }

    @Operation(
            summary = "Registrar nuevo libro",
            description = "Devuelve la información del libro correspondiente."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Libro creado"),
            @ApiResponse(responseCode = "404", description = "Libro no creado")
    })
    @PostMapping
    @Transactional
    public ResponseEntity<BookResponseDto> createBook(@Valid BookRequestDto dto) {
        CreateBookDto createBookDto = dto.toDomain();
        Book book = this.creatingBookInputPort.save(createBookDto);
        BookResponseDto response = BookResponseDto.fromDomain(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(
            summary = "Listar Libros",
            description = "Devuelve la información de todos los libros."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Libros encontrados"),
            @ApiResponse(responseCode = "404", description = "Libros no encontrados")
    })
    @GetMapping
    public ResponseEntity<List<BookResponseDto>> findAll() {
        List<BookResponseDto> booksResponse = this.listingAllBooksInputPort.findAllBooks()
                .stream()
                .map(BookResponseDto::fromDomain)
                .toList();

        return ResponseEntity.ok(booksResponse);

    }

    @Operation(
            summary = "Actualizar libro",
            description = "Devuelve la información actualizada del libro correspondiente."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Libro actualizado"),
            @ApiResponse(responseCode = "404", description = "Libro no encontrado")
    })
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<BookResponseDto> updateBook(@PathVariable UUID id, @RequestBody @Valid BookRequestDto dto) {
        UpdateBookDto updateBookDto = dto.toUpdateDomain();
        Book book = this.updatingBookInputPort.update(id, updateBookDto);
        BookResponseDto response = BookResponseDto.fromDomain(book);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Obtener libro disponible",
            description = "Obtener libro disponible por id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Libro encontrado"),
            @ApiResponse(responseCode = "404", description = "Libro no encontrado")
    })
    @GetMapping("/find-available/{id}")
    public ResponseEntity<BookResponseDto> findAvailableBookById(@PathVariable UUID id) {
        Book book = this.findingAvailableBookInputPort.findAvailableBook(id);

        return ResponseEntity.ok(BookResponseDto.fromDomain(book));
    }

    @Operation(
            summary = "Actualizar disponibilidad de libro",
            description = "Actualiza la disponibilidad de un libro en base al id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Disponibilidad actualizada."),
            @ApiResponse(responseCode = "404", description = "Disponibilidad no actualizada.")
    })
    @PutMapping("/availability/{id}")
    @Transactional
    public ResponseEntity<BookResponseDto> updateBookAvailability(@PathVariable UUID id) {
        Book book = this.updatingBookAvailabilityInputPort.updateBookAvailability(id);
        return ResponseEntity.ok(BookResponseDto.fromDomain(book));
    }
}
