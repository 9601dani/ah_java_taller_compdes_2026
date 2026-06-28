package com.compdes.book_microservice.book.application.inputports;

import com.compdes.book_microservice.book.domain.Book;

import java.util.List;

public interface ListingAllBooksInputPort {

    List<Book> findAllBooks();
}
