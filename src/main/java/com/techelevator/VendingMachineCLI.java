package com.techelevator;

import com.techelevator.view.HiddenMenu;
import com.techelevator.view.Menu;
import java.math.BigDecimal;
import java.util.Locale;

public class VendingMachineCLI {

    private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
    private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
    private static final String MAIN_MENU_OPTION_EXIT = "Exit";
    private static final String MAIN_MENU_OPTION_SALES_REPORT = "Sales Report";
    private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT, MAIN_MENU_OPTION_SALES_REPORT};

    private static final String PURCHASE_MENU_FEED_MONEY = "Feed Money";
    private static final String PURCHASE_MENU_SELECT_PRODUCT = "Select Product";
    private static final String PURCHASE_MENU_FINISH_TRANSACTION = "Finish Transaction";
    private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_FEED_MONEY, PURCHASE_MENU_SELECT_PRODUCT, PURCHASE_MENU_FINISH_TRANSACTION};


    private Menu menu;
    private Menu hiddenMenu;

    public VendingMachineCLI(Menu menu, Menu hiddenMenu) {
        this.menu = menu;
        this.hiddenMenu = hiddenMenu;
    }

    boolean menuRunning = true;

    public void run() {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.stockVendingMachine();

        while (menuRunning) {
            String choice = (String) hiddenMenu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

            if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
                // display vending machine items
                vendingMachine.printInventory();
            } else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
                // do purchase
                runPurchaseMenu(menu, vendingMachine);

            } else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
                menuRunning = false;
            } else if (choice.equals(MAIN_MENU_OPTION_SALES_REPORT)) {
                vendingMachine.generateSalesReport();

            }

        }
    }

    public void runPurchaseMenu(Menu menu, VendingMachine vendingMachine) {
        boolean purchaseMenuRunning = true;

        while (purchaseMenuRunning) {

            System.out.println();
            System.out.printf("Current Money Provided: $%.2f", vendingMachine.getCurrentMoney());

            String choiceTwo = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

            if (choiceTwo.equals(PURCHASE_MENU_FEED_MONEY)) {
                System.out.println("Please insert dollar bills only: ");
                BigDecimal insertedAmount = menu.getMoney();
                vendingMachine.addCurrentMoney(insertedAmount);
                vendingMachine.printToLog("FEED MONEY:", insertedAmount);

            } else if (choiceTwo.equals(PURCHASE_MENU_SELECT_PRODUCT)) {
                System.out.println("Please enter slot ID: ");
                String slotID = menu.getItemCode().toUpperCase(Locale.ROOT).trim();
                vendingMachine.selectSnack(slotID);

            } else if (choiceTwo.equals(PURCHASE_MENU_FINISH_TRANSACTION)) {
                //finish transaction
                BigDecimal refundAmount = vendingMachine.getCurrentMoney();
                vendingMachine.refundChange(refundAmount);
                vendingMachine.resetCurrentMoney();
                vendingMachine.printToLog("GIVE CHANGE:", refundAmount);
                purchaseMenuRunning = false;
            }
        }
    }

    public static void main(String[] args) {

        Menu menu = new Menu(System.in, System.out);
        Menu hiddenMenu = new HiddenMenu(System.in, System.out);

        VendingMachineCLI cli = new VendingMachineCLI(menu, hiddenMenu);
        cli.run();

    }
}




