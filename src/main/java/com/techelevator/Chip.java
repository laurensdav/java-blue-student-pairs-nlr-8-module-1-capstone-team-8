package com.techelevator;

import java.math.BigDecimal;

public class Chip extends Snack{

    public Chip(String name, BigDecimal price, String type) {
        super(name, price, type);
    }

    @Override
    public void SnackSound() {
        System.out.println("Crunch Crunch, Crunch!");
    }
}
