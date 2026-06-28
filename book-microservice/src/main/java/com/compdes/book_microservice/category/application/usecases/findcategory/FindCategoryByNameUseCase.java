package com.compdes.book_microservice.category.application.usecases.findcategory;

import com.compdes.book_microservice.category.application.inputports.FindingCategoryByNameInputPort;
import com.compdes.book_microservice.category.application.outputports.persistence.FindingAllCategoriesOutputPort;
import com.compdes.book_microservice.category.application.outputports.persistence.FindingCategoryByNameOutputPort;
import com.compdes.book_microservice.category.domain.Category;
import com.compdes.book_microservice.common.application.annotations.UseCase;
import com.compdes.book_microservice.common.application.exceptions.EntityNotFoundException;

@UseCase
public class FindCategoryByNameUseCase implements FindingCategoryByNameInputPort {

    private final FindingCategoryByNameOutputPort findingCategoryByNameOutputPort;

    FindCategoryByNameUseCase(FindingCategoryByNameOutputPort findingCategoryByNameOutputPort){
        this.findingCategoryByNameOutputPort = findingCategoryByNameOutputPort;
    }

    @Override
    public Category findByName(String name) throws EntityNotFoundException{
        return findingCategoryByNameOutputPort.findByName(name)
            .orElseThrow(() -> new EntityNotFoundException("La categoria no existe"));
    }
    
}
