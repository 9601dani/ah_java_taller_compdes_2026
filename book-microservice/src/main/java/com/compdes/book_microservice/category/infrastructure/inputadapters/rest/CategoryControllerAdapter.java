package com.compdes.book_microservice.category.infrastructure.inputadapters.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.compdes.book_microservice.category.application.inputports.CreatingCategoryInputPort;
import com.compdes.book_microservice.category.application.inputports.FindingCategoryByNameInputPort;
import com.compdes.book_microservice.category.application.inputports.ListingAllCategoriesInputPort;
import com.compdes.book_microservice.category.application.usecases.createcategory.CreateCategoryDto;
import com.compdes.book_microservice.category.domain.Category;
import com.compdes.book_microservice.category.infrastructure.inputadapters.rest.dto.CategoryRequestDto;
import com.compdes.book_microservice.category.infrastructure.inputadapters.rest.dto.CategoryResponseDto;
import com.compdes.book_microservice.common.infrastructure.annotations.WebAdapter;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@Tag(name = "Categories", description = "Operaciones relacionadas a las categorias de los libros")
@RestController
@RequestMapping("/v1/categories")
@WebAdapter
public class CategoryControllerAdapter {
    private final CreatingCategoryInputPort creatingCategoryInputPort;
    private final ListingAllCategoriesInputPort listingAllCategoriesInputPort;
    private final FindingCategoryByNameInputPort findingCategoryByNameInputPort;

    public CategoryControllerAdapter(CreatingCategoryInputPort creatingCategoryInputPort, ListingAllCategoriesInputPort listingAllCategoriesInputPort,
        FindingCategoryByNameInputPort findingCategoryByNameInputPort
    ){
        this.creatingCategoryInputPort = creatingCategoryInputPort;
        this.listingAllCategoriesInputPort = listingAllCategoriesInputPort;
        this.findingCategoryByNameInputPort = findingCategoryByNameInputPort;
    }

    @Operation(
            summary = "Registrar nueva categoria",
            description = "Devuelve la información de la categoria correspondiente."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoria creado"),
            @ApiResponse(responseCode = "404", description = "Categoria no creado")
    })
    @PostMapping
    @Transactional
    public ResponseEntity<CategoryResponseDto> createCategory(@RequestBody CategoryRequestDto dto) {
        CreateCategoryDto objectFromRestToDomain = dto.toDomain();

        Category category = creatingCategoryInputPort.save(objectFromRestToDomain);

        CategoryResponseDto response = CategoryResponseDto.fromDomain(category);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(
            summary = "Obtener las categorias existentes",
            description = "Devuelve la información de las categorias correspondiente."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categorias encontradas"),
            @ApiResponse(responseCode = "404", description = "Categorias no encontradas")
    })
    @GetMapping()
    @Transactional
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories() {

        List<CategoryResponseDto> categories = listingAllCategoriesInputPort.getAllCategories()
                .stream()
                .map(CategoryResponseDto::fromDomain)
                .toList();

        return ResponseEntity.ok(categories);
    }

    @Operation(
            summary = "Obtener la categoria por nombre",
            description = "Devuelve la información de la categoria correspondiente."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categorias encontrada"),
            @ApiResponse(responseCode = "404", description = "Categorias no encontrada")
    })
    @GetMapping("/{name}")
    @Transactional
    public ResponseEntity<CategoryResponseDto> getCateogryByName(@PathVariable String id) {
        Category category = findingCategoryByNameInputPort.findByName(id);

        return ResponseEntity.ok(CategoryResponseDto.fromDomain(category));
    }
    
    
}
