package com.compdes.book_microservice.category.infrastructure.inputadapters.rest.dto;

import com.compdes.book_microservice.category.application.usecases.createcategory.CreateCategoryDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class CategoryRequestDto {
    @NotBlank
    private String name;

    public CreateCategoryDto toDomain(){
        return new CreateCategoryDto(name);
    }
}
