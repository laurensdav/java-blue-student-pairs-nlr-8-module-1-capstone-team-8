package com.techelevator;

import java.math.BigDecimal;

public class Drink extends Snack{
    public Drink(String name, BigDecimal price) {
        super(name, price);
    }

    @Override
    public void SnackSound() {
        System.out.println("Cheers Glug, Glug!");
    }
}
