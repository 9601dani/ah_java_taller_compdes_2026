package com.compdes.book_microservice.category.application.usecases.listcategories;

import java.util.List;

import com.compdes.book_microservice.category.application.inputports.ListingAllCategoriesInputPort;
import com.compdes.book_microservice.category.application.outputports.persistence.FindingAllCategoriesOutputPort;
import com.compdes.book_microservice.category.domain.Category;
import com.compdes.book_microservice.common.application.annotations.UseCase;

@UseCase
public class ListAllCategories implements ListingAllCategoriesInputPort {

    private final FindingAllCategoriesOutputPort findingAllCategoriesOutputPort;

    public ListAllCategories(FindingAllCategoriesOutputPort findingAllCategoriesOutputPort){
        this.findingAllCategoriesOutputPort = findingAllCategoriesOutputPort;
    }

    @Override
    public List<Category> getAllCategories(){
        return findingAllCategoriesOutputPort.findAllCategories();
    }
    
}
