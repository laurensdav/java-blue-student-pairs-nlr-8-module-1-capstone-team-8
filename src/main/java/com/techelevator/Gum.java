package com.techelevator;

import java.math.BigDecimal;

public class Gum extends Snack{

    public Gum(String name, BigDecimal price) {
        super(name, price);
    }

    @Override
    public void snackSound() {
        System.out.println("Chew Chew, Pop!");
    }
}
