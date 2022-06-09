package com.techelevator;

import java.math.BigDecimal;

public class Snack {

    private String name;
    private BigDecimal price;
    private String type;
    private int quantity = 5;



    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public int getQuantity() {

        return quantity;
    }

    public void subtractQuantity () {
        this.quantity--;
    }


    public Snack(String name, BigDecimal price, String type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }
}
