package com.indocyber.jpa.category.dto;

import lombok.Data;

@Data
public class CategoryResponse {
    private final Integer id;
    private final String name;
    private final String description;
}
