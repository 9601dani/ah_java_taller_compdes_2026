package com.compdes.book_microservice.book.application.outputports.persistence;

import com.compdes.book_microservice.book.domain.Book;

import java.util.List;

public interface FindingAllBooksOutputPort {

    List<Book> findAllBooks();
}
