package com.indocyber.validation.person;

import com.indocyber.validation.user.User;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Person {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String email;
    private String phoneNumber;
    private String address;
    private String postalCode;
    private Boolean isMarried;
    private Integer yearsExperience;
    private Integer totalChildren;
    private BigDecimal estimatedSalary;

    private User user;
}
