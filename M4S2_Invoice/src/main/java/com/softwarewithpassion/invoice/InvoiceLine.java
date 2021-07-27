package com.softwarewithpassion.invoice;

import java.math.BigDecimal;

public class InvoiceLine {
    private final String description;
    private final BigDecimal rate;
    private final BigDecimal energyConsumed;
    private final BigDecimal totalAmount;

    public InvoiceLine(String description, BigDecimal rate, BigDecimal energyConsumed, BigDecimal totalAmount) {
        this.description = description;
        this.rate = rate;
        this.energyConsumed = energyConsumed;
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    @Override
    public String toString() {
        return "InvoiceLine{" +
                "description='" + description + '\'' +
                ", rate=" + rate +
                ", energyConsumed=" + energyConsumed +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
