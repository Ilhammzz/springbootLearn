package com.indocyber.jpa.category;

import com.indocyber.jpa.category.dto.PricePerCategoryResponse;
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

    @OneToMany(mappedBy = "category")
    private List<Product> products;

}
