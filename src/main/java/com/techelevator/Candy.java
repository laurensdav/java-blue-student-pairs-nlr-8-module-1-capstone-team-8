package com.techelevator;

import java.math.BigDecimal;

public class Candy extends Snack{


    public Candy(String name, BigDecimal price) {
        super(name, price);
    }

    @Override
    public void snackSound() {
        System.out.println("Munch Munch, Mmm-Good!");
    }
}
