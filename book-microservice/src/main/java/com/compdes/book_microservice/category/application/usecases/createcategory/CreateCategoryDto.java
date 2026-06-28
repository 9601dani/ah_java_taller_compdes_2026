package com.compdes.book_microservice.category.application.usecases.createcategory;

import com.compdes.book_microservice.category.domain.Category;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class CreateCategoryDto {
    private String name;

    public Category toDomain(){
        return new Category(name.toUpperCase());
    }
}
