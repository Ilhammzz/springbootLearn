package com.indocyber.jpa.order;

import com.indocyber.jpa.customer.Customer;
import com.indocyber.jpa.orderdetails.OrderDetails;
import com.indocyber.jpa.salesman.Salesman;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Orders")
@ToString
public class Order {
    @Id
    @Column(name = "InvoiceNumber")
    private String invoiceNumber;
    @Column(name = "OrderDate")
    private LocalDate orderDate;
    @Column(name = "ShippedDate")
    private LocalDate shippedDate;
    @Column(name = "DueDate")
    private LocalDate dueDate;
    @Column(name = "DeliveryCost")
    private BigDecimal deliverCost;
    @Column(name = "DeliveryAddress")
    private String deliveryAddress;
    @Column(name = "DestinationCity")
    private String destinationCity;
    @Column(name = "DestinationPostalCode")
    private String destinationPostalCode;

    //Uni-directional
    @ManyToOne
    @JoinColumn(name = "CustomerId")
    private Customer customer;

    //Uni-directional
    @ManyToOne
    @JoinColumn(name = "salesEmployeeNumber")
    private Salesman salesman;

    //Bi-Directional
    @OneToMany(mappedBy = "order")
    private List<OrderDetails> orderDetailsList;
}
