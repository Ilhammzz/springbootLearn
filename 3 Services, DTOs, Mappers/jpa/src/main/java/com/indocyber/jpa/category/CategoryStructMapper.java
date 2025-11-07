package com.indocyber.jpa.category;

import com.indocyber.jpa.category.dto.CategoryListResponse;
import com.indocyber.jpa.category.dto.CategoryResponse;
import com.indocyber.jpa.category.dto.UpsertCategoryRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryStructMapper {
    CategoryListResponse toListDto(Category entity);

    CategoryResponse toDto(Category entity);

    Category toEntity(UpsertCategoryRequest dto);

    void updateEntityFromDto(@MappingTarget Category category, UpsertCategoryRequest dto);
}
