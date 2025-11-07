package com.indocyber.rest.subject.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class SubjectListResponse {
    private final String id;
    private final String name;
    private final Integer credit;
    private final BigDecimal price;
    private final Boolean active;
}
