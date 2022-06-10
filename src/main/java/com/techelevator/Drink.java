package com.techelevator;

import java.math.BigDecimal;

public class Drink extends Snack{
    public Drink(String name, BigDecimal price, String type) {
        super(name, price, type);
    }

    @Override
    public void SnackSound() {
        System.out.println("Cheers Glug, Glug!");
    }
}
