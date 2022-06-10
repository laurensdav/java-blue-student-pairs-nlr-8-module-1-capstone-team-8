package com.techelevator;

import java.math.BigDecimal;

public class Candy extends Snack{


    public Candy(String name, BigDecimal price, String type) {
        super(name, price, type);
    }

    @Override
    public void SnackSound() {
        System.out.println("Munch Munch, Mmm-Good!");
    }
}
