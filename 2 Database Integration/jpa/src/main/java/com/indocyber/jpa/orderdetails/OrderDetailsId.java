package com.indocyber.jpa.orderdetails;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class OrderDetailsId {
    private String invoiceNumber;
    private Integer productId;
}
