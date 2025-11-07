package com.indocyber.jpa.region;

import com.indocyber.jpa.salesman.Salesman;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Regions")
@ToString
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "City", nullable = false)
    private String city;
    @Column(name = "Remark", nullable = false)
    private String remark;

    @ManyToMany(mappedBy = "regions")
    private Set<Salesman> salesmen;
}
