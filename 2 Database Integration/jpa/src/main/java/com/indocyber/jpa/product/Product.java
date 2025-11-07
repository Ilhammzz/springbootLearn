package com.indocyber.jpa.product;

import com.indocyber.jpa.category.Category;
import com.indocyber.jpa.orderdetails.OrderDetails;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Products")
@ToString
public class Product {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "Name")
    private String name;
    @Column(name = "Description")
    private String description;
    @Column(name = "Price")
    private BigDecimal price;
    @Column(name = "UnitsInStock")
    private Integer unitsInStock;
    @Column(name = "OnOrder")
    private Integer onOrder;
    @Column(name = "Discontinue")
    private Boolean discontinue;

    @ManyToOne
    @JoinColumn(name = "CategoryId")
    private Category category;

    @ToString.Exclude
    @OneToMany(mappedBy = "product")
    private List<OrderDetails> orderDetailsList;

    @PrePersist
    void onCreate() {
        discontinue = discontinue == null ? false : discontinue;
    }
}
