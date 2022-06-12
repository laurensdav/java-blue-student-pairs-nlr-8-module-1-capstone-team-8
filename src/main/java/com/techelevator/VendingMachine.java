package com.techelevator;

import java.io.*;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class VendingMachine {

    Map<String, Snack> inventory = new LinkedHashMap<>();
    BigDecimal totalSales = BigDecimal.valueOf(0);
    BigDecimal currentMoney = BigDecimal.valueOf(0);

    // Getters
    public BigDecimal getTotalSales() {
        return totalSales.setScale(2);
    }

    public BigDecimal getCurrentMoney() {
        return currentMoney.setScale(2);
    }

    // Stock vending and create Snack objects
    public void stockVendingMachine () {
        File inputFile = new File("vendingmachine.csv");
        if (inputFile.exists()) {
            try (Scanner scanner = new Scanner(inputFile)) {
                while (scanner.hasNextLine()) {
                    String lineFromFile = scanner.nextLine();
                    String [] lineArray = lineFromFile.split("\\|");

                    Snack newSnack = getSnackType(lineArray[1], new BigDecimal(lineArray[2]), lineArray[3]);
                    inventory.put( lineArray[0], newSnack);
                }
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        } else if (!inputFile.exists()) System.out.println("file not found");
    }

    public Snack getSnackType(String name, BigDecimal price, String type) {
        if (type.equals("Candy") ) {
            return new Candy(name, price);
        } if (type.equals("Chip")) {
            return new Chip(name, price);
        } if (type.equals("Drink")) {
            return new Drink(name, price);
        } if (type.equals("Gum")) {
            return new Gum(name, price);
        }
        return null;
    }

    // Display inventory to user
    public void printInventory() {
        for (Map.Entry<String, Snack> entry : inventory.entrySet()) {
            String slotNumber = entry.getKey();
            String name = entry.getValue().getName();
            BigDecimal price = entry.getValue().getPrice();
            int quantity = entry.getValue().getQuantity();

            if (quantity > 0) {

                System.out.println("(" + slotNumber + ") " + name + " $" + price + " quantity: " + quantity);
            } else System.out.println("Sold Out");
        }
    }

    // Purchase methods
    public void addSale(BigDecimal purchase) {
        totalSales = totalSales.add(purchase);
    }

    public void addCurrentMoney(BigDecimal insertedMoney) {
        currentMoney = currentMoney.add(insertedMoney);
    }

    public void subtractCurrentMoney(BigDecimal costOfSnack) {
        currentMoney = currentMoney.subtract(costOfSnack);
    }

    public void resetCurrentMoney() {
        currentMoney = BigDecimal.valueOf(0);
    }


    public void selectSnack(String slotID) {
        if (inventory.containsKey(slotID)) {
            Snack currentSnack = inventory.get(slotID);

            if ((getCurrentMoney().compareTo(currentSnack.getPrice()) == (0)) || (getCurrentMoney().compareTo(currentSnack.getPrice()) == 1 )) {
                if ((currentSnack.getQuantity() > 0)) {

                    currentSnack.subtractQuantity();

                    subtractCurrentMoney(currentSnack.getPrice());
                    addSale(currentSnack.getPrice());

                    printToLog(currentSnack.getName() + " " + slotID, currentSnack.getPrice());
                    System.out.println("Dispensing: " + currentSnack.getName() + ", $" + currentSnack.getPrice());
                    System.out.println("Remaining Money: $" + getCurrentMoney());
                    currentSnack.snackSound();
                } else if (currentSnack.getQuantity() <=0) {
                    System.out.println("Sold Out");
                }
            } else System.out.println("Error: please insert more money");
        } else System.out.println("Error: please select valid product code");
    }

    public void refundChange(BigDecimal change) {

        BigDecimal quarter = BigDecimal.valueOf(0.25);
        BigDecimal dime = BigDecimal.valueOf(0.10);
        BigDecimal nickel = BigDecimal.valueOf(0.05);

        int refundedQuarters = change.divide(quarter).intValue();
        int refundedDimes = change.remainder(quarter).divide(dime).intValue();
        int refundedNickels = change.remainder(quarter).remainder(dime).divide(nickel).intValue();

        String refundMessage = "Refunding: ";

        if (refundedQuarters == 0) {
            refundMessage += "$0.00";
        }
        if (refundedQuarters > 0) {
            refundMessage += refundedQuarters + " quarter(s) ";
        }
        if (refundedDimes > 0) {
            refundMessage += refundedDimes + " dime(s) ";
        }
        if (refundedNickels > 0) {
            refundMessage += refundedNickels + " nickel(s)";
        }

        System.out.println(refundMessage);

    }

    // Print to log
    public void printToLog(String transaction, BigDecimal transactionAmount) {
        File logFile = new File("Log.txt");
        boolean append = logFile.exists();

        try (PrintWriter writer = new PrintWriter(new FileOutputStream(logFile, append))) {
            DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
            String currentTime = formatter.format(new Date()).toString();

            writer.append(currentTime + " " + transaction + " $" + transactionAmount.setScale(2) + " $" + getCurrentMoney() + "\n" );
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // Generate sales report
    public void generateSalesReport() {
        DateFormat formatter = new SimpleDateFormat("MM-dd-yyyy_HH-mm-ss");
        String currentTime = formatter.format(new Date()).toString();
        String fileName = "sales_report_" + currentTime + ".txt";
        File salesReport = new File(fileName);

        try (PrintWriter writer = new PrintWriter(salesReport)) {
            for (Map.Entry<String, Snack> entry : inventory.entrySet()) {
                int quantitySold = 5 - entry.getValue().getQuantity();
                writer.println(entry.getValue().getName() + "|" + quantitySold);
            }

            writer.println();
            writer.println("**TOTAL SALES** $" + getTotalSales());
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }
}

