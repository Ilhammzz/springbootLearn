package com.indocyber.rest.subject.dto;

import com.indocyber.rest.subject.Level;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class SubjectUpdateRequest {
    @NotBlank
    @NotNull
    @Size(max = 50)
    private final String name;
    private final String description;
    @NotNull
    @Min(1)
    @Max(6)
    private final Integer credit;
    @NotNull
    @DecimalMin(value = "0")
    @DecimalMax(value = "999999.99")
    private final BigDecimal price;
    @NotNull
    private final Level level;
    @NotNull
    private final Boolean active;
}
