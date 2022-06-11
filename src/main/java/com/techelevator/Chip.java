package com.techelevator;

import java.math.BigDecimal;

public class Chip extends Snack{

    public Chip(String name, BigDecimal price) {
        super(name, price);
    }

    @Override
    public void snackSound() {
        System.out.println("Crunch Crunch, Crunch!");
    }
}
