package com.indocyber.jpa.salesman;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Level {
    INSIDE_SALES_REPRESENTATIVE("Inside Sales Representative"),
    OUTSIDE_SALES_REPRESENTATIVE("Outside Sales Representative"),
    REGIONAL_SALES_DIRECTOR("Regional Sales Director"),
    RETAIL_SALES("Retail Sales"),
    SALES_ASSISTANT("Sales Assistant"),
    SALES_ENGINEER("Sales Engineer"),
    SALES_MANAGER("Sales Manager"),
    WHOLESALE_SALES("Wholesale Sales");

    private final String label;
}
