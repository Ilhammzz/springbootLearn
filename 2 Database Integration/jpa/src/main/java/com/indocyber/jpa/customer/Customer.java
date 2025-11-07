package com.indocyber.jpa.customer;

import com.indocyber.jpa.customerdetails.CustomerDetails;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Customers")
@ToString
public class Customer {
    @Id
    @Column(name = "Id")
    private String id;

    @Column(name = "CompanyName")
    private String companyName;

    @Column(name = "ContactPerson")
    private String contactPerson;

    @Column(name = "DeleteDate")
    private LocalDate deleteDate;

    @OneToOne(mappedBy = "customer")
    private CustomerDetails customerDetails;
}
