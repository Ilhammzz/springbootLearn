package com.indocyber.security.user;

import com.indocyber.security.person.Person;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "Users")
public class User {
    @Id
    private String username;
    @Column(name = "Password")
    private String password;
    @Column(name = "RegisterDate")
    private LocalDate registerDate;
    @Column(name = "Role")
    private Role role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Person person;

    @PrePersist
    private void onCreate() {
        if (registerDate == null) {
            this.registerDate = LocalDate.now();
        }
    }
}
