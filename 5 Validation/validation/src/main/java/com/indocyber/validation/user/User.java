package com.indocyber.validation.user;

import com.indocyber.validation.person.Person;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class User {
    private String username;
    private String password;
    private LocalDate registerDate;

    private Person person;
}
