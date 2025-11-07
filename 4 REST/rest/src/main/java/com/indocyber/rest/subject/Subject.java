package com.indocyber.rest.subject;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "Subjects")
public class Subject {
    @Id
    @Column(name = "Id", length = 2)
    private String id;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Description")
    private String description;

    @Column(name = "CreatedDate")
    private LocalDate createdDate;

    @Column(name = "Credit", nullable = false)
    private Integer credit;

    @Column(name = "Price", nullable = false)
    private BigDecimal price;

    @Column(name = "Level", nullable = false)
    @Enumerated(EnumType.STRING)
    private Level level;

    @Column(name = "Active", nullable = false)
    private Boolean active;

    @PrePersist
    private void onCreate() {
        id = id.toUpperCase();
        active = true;
        createdDate = LocalDate.now();
    }
}
