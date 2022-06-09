package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class VendingMachine {

    Map<String, Snack> inventory = new HashMap<>();


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

}
