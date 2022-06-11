package com.techelevator;

import java.math.BigDecimal;

public abstract class Snack {

    private String name;
    private BigDecimal price;
    private int quantity;

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price.setScale(2);
    }

    public int getQuantity() {
        return quantity;
    }

    public void subtractQuantity () {
        this.quantity--;
    }


    public Snack(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
        this.quantity = 5;
    }

    public abstract void snackSound();


}
