package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.*;

public class VendingMachine {

    Map<String, Snack> inventory = new LinkedHashMap<>();
    BigDecimal totalSales = BigDecimal.valueOf(0);
    BigDecimal currentMoney = BigDecimal.valueOf(0);

    public BigDecimal getTotalSales() {
        return totalSales;
    }

    public BigDecimal getCurrentMoney() {
        return currentMoney;
    }

    public void stockVendingMachine () throws FileNotFoundException {
        File inputFile = new File("vendingmachine.csv");
        if (inputFile.exists()) {
            try (Scanner scanner = new Scanner(inputFile)) {
                while (scanner.hasNextLine()) {
                    String lineFromFile = scanner.nextLine();
                    String [] lineArray = lineFromFile.split("\\|");
                    Snack newSnack = new Snack(lineArray[1], new BigDecimal(lineArray[2]), lineArray[3]);
                    inventory.put( lineArray[0], newSnack);
                }
            }
        }else if (!inputFile.exists()) System.out.println("file not found");
    }

    public void printInventory() {
        for (Map.Entry<String, Snack> entry : inventory.entrySet()) {
            String slotNumber = entry.getKey();
            String name = entry.getValue().getName();
            BigDecimal price = entry.getValue().getPrice();
            int quantity = entry.getValue().getQuantity();

            System.out.println("(" + slotNumber + ") " + name + " $" + price + " quantity: " + quantity);
        }
    }

    public void addSale(BigDecimal purchase) {
        totalSales = totalSales.add(purchase);
    }

    public void addCurrentMoney(BigDecimal insertedMoney) {
        currentMoney = currentMoney.add(insertedMoney);
    }

    public void subtractCurrentMoney(BigDecimal costOfSnack) {
        currentMoney = currentMoney.subtract(costOfSnack);
    }

    public void selectSnack(String slotID) {
        if (inventory.containsKey(slotID)) {
           Snack currentSnack = inventory.get(slotID);
           currentSnack.subtractQuantity();
           subtractCurrentMoney(currentSnack.getPrice());
           addSale(currentSnack.getPrice());

            System.out.println("Dispensing: " + currentSnack.getName() + ", $" + currentSnack.getPrice());
            System.out.println("Remaining money: $" + getCurrentMoney());

        }
    }
}
