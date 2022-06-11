package com.techelevator;

import java.math.BigDecimal;

public class Drink extends Snack{
    public Drink(String name, BigDecimal price) {
        super(name, price);
    }

    @Override
    public void snackSound() {
        System.out.println("Cheers Glug, Glug!");
    }
}
