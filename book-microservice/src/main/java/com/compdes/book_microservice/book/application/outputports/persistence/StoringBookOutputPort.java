package com.compdes.book_microservice.book.application.outputports.persistence;

import com.compdes.book_microservice.book.domain.Book;


public interface StoringBookOutputPort {

    Book save(Book book);
}
