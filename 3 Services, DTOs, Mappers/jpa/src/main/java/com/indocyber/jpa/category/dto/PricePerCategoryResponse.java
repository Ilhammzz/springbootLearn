package com.indocyber.jpa.category.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PricePerCategoryResponse {
    private final Integer id;
    private final String name;
    private final String description;
    private final BigDecimal avgPrice;
}
