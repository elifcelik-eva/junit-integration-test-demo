package com.elifcelik.testlab.unitTestCases.hardDemo;

public class Order {
    private Long id;
    private double totalAmount;

    public Order(Long id, double totalAmount) {
        this.id = id;
        this.totalAmount = totalAmount;
    }

    public Long getId() {
        return id;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
