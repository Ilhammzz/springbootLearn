package com.indocyber.jpa.orderdetails;

import com.indocyber.jpa.order.Order;
import com.indocyber.jpa.product.Product;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "OrderDetails")
@ToString
public class OrderDetails {
    @EmbeddedId
    private OrderDetailsId orderDetailsId;

    @Column(name = "UnitPrice")
    private BigDecimal unitPrice;
    @Column(name = "Quantity")
    private Integer quantity;
    @Column(name = "Discount")
    private Double discount;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "ProductId")
    private Product product;

    @ManyToOne
    @MapsId("invoiceNumber")
    @JoinColumn(name = "InvoiceNumber")
    private Order order;
}
