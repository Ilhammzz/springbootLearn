package com.indocyber.rest.subject.dto;

import com.indocyber.rest.subject.Level;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class SubjectResponse {
    private final String id;
    private final String name;
    private final String description;
    private final LocalDate createdDate;
    private final Integer credit;
    private final BigDecimal price;
    private final Level level;
    private final Boolean active;
}
