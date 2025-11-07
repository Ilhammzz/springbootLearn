package com.indocyber.jpa.category;

import com.indocyber.jpa.product.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Categories")
@ToString
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "Name", nullable = false, unique = true, length = 50)
    private String name;
    @Column(name = "Description")
    private String description;

    //Bidirectional
    @ToString.Exclude
    @OneToMany(mappedBy = "category", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    private List<Product> products;

    /*
     * cascade allows us to do stuff (based on its type we use)
     * some of the useful cascade types to know are:
     * PERSIST -> inserts the related entity when the main entity is saved
     * MERGE -> updates the 'existing' related entity when the main entity is saved
     * REMOVE -> deletes the related entity when the main entity is deleting (BE CAUTIOUS WHEN USING THIS)
     *
     * cascade is HIGHLY OPTIONAL, operations can be done without it normally by calling other repositories.
     */
}
