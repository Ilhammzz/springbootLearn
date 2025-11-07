package com.indocyber.jpa.category.dto;

import lombok.Data;

//for requests, final properties are more optional.
@Data
public class UpsertCategoryRequest {
    private final String name;
    private final String description;
}
