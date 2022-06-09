package com.techelevator;

import com.techelevator.view.Menu;

import java.io.FileNotFoundException;
import java.math.BigDecimal;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };

	private static final String PURCHASE_MENU_FEED_MONEY = "Please feed money";
	private static final String PURCHASE_MENU_SELECT_PRODUCT = "Select product";
	private static final String PURCHASE_MENU_FINISH_TRANSACTION = "Finish transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_FEED_MONEY, PURCHASE_MENU_SELECT_PRODUCT, PURCHASE_MENU_FINISH_TRANSACTION };


	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	boolean menuRunning = true;

	public void run() throws FileNotFoundException {
		VendingMachine vendingMachine = new VendingMachine();
		vendingMachine.stockVendingMachine();

		while (menuRunning) {
			System.out.println(vendingMachine.getTotalSales());
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
				vendingMachine.printInventory();
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				// do purchase
				runPurchaseMenu(menu, vendingMachine);

			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
					menuRunning = false;
				}
			}
		}


	public static void main(String[] args) throws FileNotFoundException {

		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();

	}

	public void runPurchaseMenu(Menu menu, VendingMachine vendingMachine) {
		boolean purchaseMenuRunning = true;
		String slotID;

		while (purchaseMenuRunning) {

			System.out.println();
			System.out.printf("Current Money Provided: $%.2f", vendingMachine.getCurrentMoney());

			String choiceTwo = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

			if (choiceTwo.equals(PURCHASE_MENU_FEED_MONEY)) {
				System.out.println("Please insert dollar bills only: ");
				vendingMachine.addCurrentMoney(menu.getMoney());

			} else if (choiceTwo.equals(PURCHASE_MENU_SELECT_PRODUCT)) {
				System.out.println("Please enter slot ID: ");
				slotID = menu.getItemCode();
				vendingMachine.selectSnack(slotID);

			} else if (choiceTwo.equals(PURCHASE_MENU_FINISH_TRANSACTION)) {
				//finish transaction
				System.out.println("Refunding: $" + vendingMachine.getCurrentMoney());
				purchaseMenuRunning = false;
			}
		}
	}







//	String choiceTwo = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
//	while {String choiceTwo = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
//			if (!choiceTwo.equals(PURCHASE_MENU_FINISH_TRANSACTION))
//
//	}




	}




