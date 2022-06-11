package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;

public class VendingMachineTest {

    VendingMachine testVendingMachine = new VendingMachine();

    @Before public void setup() throws FileNotFoundException {

        testVendingMachine.stockVendingMachine();
        testVendingMachine.addCurrentMoney(BigDecimal.valueOf(10.00));
        testVendingMachine.selectSnack("C2");
    }

    @Test
    public void testing_current_money () {
        BigDecimal expected = BigDecimal.valueOf(8.50).setScale(2);
        Assert.assertEquals(expected, testVendingMachine.getCurrentMoney());
    }

    @Test
    public void testing_total_sales () {
        BigDecimal expected = BigDecimal.valueOf(1.50).setScale(2);
        Assert.assertEquals(expected, testVendingMachine.getTotalSales());
    }

    @Test
    public void testing_sold_out_quantity() {
        testVendingMachine.selectSnack("C2");
        testVendingMachine.selectSnack("C2");
        testVendingMachine.selectSnack("C2");
        testVendingMachine.selectSnack("C2");
        testVendingMachine.selectSnack("C2");

        int expected = 0;

        Assert.assertEquals(expected, testVendingMachine.inventory.get("C2").getQuantity());
    }





}
