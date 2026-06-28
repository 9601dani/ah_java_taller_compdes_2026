package com.compdes.book_microservice.category.application.inputports;

import com.compdes.book_microservice.category.application.usecases.createcategory.CreateCategoryDto;
import com.compdes.book_microservice.category.domain.Category;
import com.compdes.book_microservice.common.application.exceptions.EntityAlreadyExistException;

public interface CreatingCategoryInputPort {
    Category save(CreateCategoryDto dto) throws EntityAlreadyExistException;   
}
