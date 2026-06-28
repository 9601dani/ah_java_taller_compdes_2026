package com.compdes.book_microservice.book.application.usecases.listbooks;

import com.compdes.book_microservice.book.application.inputports.ListingAllBooksInputPort;
import com.compdes.book_microservice.book.application.outputports.persistence.FindingAllBooksOutputPort;
import com.compdes.book_microservice.book.domain.Book;
import com.compdes.book_microservice.common.application.annotations.UseCase;

import java.util.List;

@UseCase
public class ListAllBooksUseCase implements ListingAllBooksInputPort {

    private final FindingAllBooksOutputPort findingAllBooksOutputPort;

    public ListAllBooksUseCase(FindingAllBooksOutputPort findingAllBooksOutputPort) {
        this.findingAllBooksOutputPort = findingAllBooksOutputPort;
    }

    @Override
    public List<Book> findAllBooks() {
        return this.findingAllBooksOutputPort.findAllBooks();
    }
}
