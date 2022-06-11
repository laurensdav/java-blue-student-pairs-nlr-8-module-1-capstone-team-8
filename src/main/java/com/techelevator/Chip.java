package com.techelevator;

import java.math.BigDecimal;

public class Chip extends Snack{

    public Chip(String name, BigDecimal price) {
        super(name, price);
    }

    @Override
    public void SnackSound() {
        System.out.println("Crunch Crunch, Crunch!");
    }
}
