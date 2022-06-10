package com.techelevator;

import java.math.BigDecimal;

public class Gum extends Snack{
    public Gum(String name, BigDecimal price, String type) {
        super(name, price, type);
    }

    @Override
    public void SnackSound() {
        System.out.println("Chew Chew, Pop!");
    }
}
