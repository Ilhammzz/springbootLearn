package com.indocyber.jpa.customerdetails;

import com.indocyber.jpa.customer.Customer;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "CustomerDetails")
@ToString
public class CustomerDetails {
    @Id
    @Column(name = "Id")
    private String id;
    @Column(name = "Address")
    private String address;
    @Column(name = "City", length = 100)
    private String city;
    @Column(name = "Phone", unique = true, length = 20)
    private String phone;
    @Column(name = "Email", unique = true, length = 100)
    private String email;

    @OneToOne
    @JoinColumn(name = "Id")
    private Customer customer;
}
