package com.indocyber.jpa.category;

import com.indocyber.jpa.category.dto.CategoryListResponse;
import com.indocyber.jpa.category.dto.CategoryResponse;
import com.indocyber.jpa.category.dto.UpsertCategoryRequest;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    CategoryListResponse toListDto(Category entity) {
        return new CategoryListResponse(entity.getId(), entity.getName());
    }

    CategoryResponse toDto(Category entity) {
        return new CategoryResponse(entity.getId(), entity.getName(), entity.getDescription());
    }

    Category toEntity(UpsertCategoryRequest dto) {
        var category = new Category();
        category.setId(0);
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        return category;
    }

    void updateEntityFromDto(Category category, UpsertCategoryRequest dto) {
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
    }
}
