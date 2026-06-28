package com.compdes.book_microservice.category.application.inputports;


import com.compdes.book_microservice.category.domain.Category;
import com.compdes.book_microservice.common.application.exceptions.EntityNotFoundException;

public interface FindingCategoryByNameInputPort {
    Category findByName(String name) throws EntityNotFoundException;
}
