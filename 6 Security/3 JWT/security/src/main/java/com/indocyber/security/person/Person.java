package com.indocyber.security.person;

import com.indocyber.security.user.User;
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
@Table(name = "Persons")
public class Person {
    @Id
    private String username;
    @Column(name = "FirstName")
    private String firstName;
    @Column(name = "LastName")
    private String lastName;
    @Column(name = "BirthDate")
    private LocalDate birthDate;
    @Column(name = "Email")
    private String email;
    @Column(name = "PhoneNumber")
    private String phoneNumber;
    @Column(name = "Address")
    private String address;
    @Column(name = "PostalCard")
    private String postalCode;
    @Column(name = "IsMarried")
    private Boolean isMarried;
    @Column(name = "YearsExperience")
    private Integer yearsExperience;
    @Column(name = "ToTalChildren")
    private Integer totalChildren;
    @Column(name = "EstimatedSalary")
    private BigDecimal estimatedSalary;

    @OneToOne
    @JoinColumn(name = "Username")
    @MapsId
    private User user;
}
