package com.indocyber.jpa.salesman;

import com.indocyber.jpa.region.Region;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Salesmen")
@ToString
public class Salesman {
    @Id
    @Column(name = "EmployeeNumber")
    private String employeeNumber;

    @Column(name = "FirstName", nullable = false)
    private String firstName;
    @Column(name = "LastName")
    private String lastName;
    @Column(name = "Level")
    @Enumerated(EnumType.STRING)
    private Level level;
    @Column(name = "BirthDate", nullable = false)
    private LocalDate birthDate;
    @Column(name = "HiredDate", nullable = false)
    private LocalDate hiredDate;

    @ManyToOne
    @JoinColumn(name = "SuperiorEmployeeNumber")
    private Salesman superiorEmployeeNumber;

    @ManyToMany
    @JoinTable(name = "SalesmenRegions",
            joinColumns = @JoinColumn(name = "CustomerId"),
            inverseJoinColumns = @JoinColumn(name = "RegionId"))
    private Set<Region> regions;
}
