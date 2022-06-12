package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.math.BigDecimal;

public class SnackTest {

    VendingMachine testVendingMachine = new VendingMachine();

    @Before
    public void setup() {
        testVendingMachine.stockVendingMachine();

    }

    @Test
    public void testing_correct_snack_found() {
        String expected = new Drink("Mountain Melter", BigDecimal.valueOf(1.50)).getName();
        String tested = testVendingMachine.inventory.get("C3").getName();

        Assert.assertEquals(expected, tested);
    }

}
