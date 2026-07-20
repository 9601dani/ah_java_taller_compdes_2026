package com.compdes.book_microservice.category.application.usecases.createcategory;


import com.compdes.book_microservice.category.application.inputports.CreatingCategoryInputPort;
import com.compdes.book_microservice.category.application.outputports.persistence.FindingCategoryByNameOutputPort;
import com.compdes.book_microservice.category.application.outputports.persistence.StoringCategoryOutputPort;
import com.compdes.book_microservice.category.domain.Category;
import com.compdes.book_microservice.common.application.annotations.UseCase;
import com.compdes.book_microservice.common.application.exceptions.EntityAlreadyExistException;

@UseCase
public class CreateCategoryUseCase implements CreatingCategoryInputPort{

    private final FindingCategoryByNameOutputPort findingCategoryByNameOutputPort;
    private final StoringCategoryOutputPort storingCategoryOutputPort;

    public CreateCategoryUseCase(FindingCategoryByNameOutputPort findingCategoryByNameOutputPort,
        StoringCategoryOutputPort storingCategoryOutputPort){
            this.findingCategoryByNameOutputPort = findingCategoryByNameOutputPort;
            this.storingCategoryOutputPort = storingCategoryOutputPort;
        }


    @Override
    public Category save(CreateCategoryDto dto) throws EntityAlreadyExistException{
       Category newCategory = dto.toDomain();

       if(findingCategoryByNameOutputPort.findByName(newCategory.getName().getName()).isPresent()){
        throw new EntityAlreadyExistException("La categoria "+ dto.getName()+" ya existe");
       }

       return storingCategoryOutputPort.save(newCategory);
    }
    
}
