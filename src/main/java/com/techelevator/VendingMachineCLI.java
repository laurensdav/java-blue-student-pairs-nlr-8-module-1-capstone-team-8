package com.techelevator;

import com.techelevator.view.Menu;

import java.io.FileNotFoundException;

public class VendingMachineCLI<purchaseMenueRunning> {

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
	boolean purchaseMenuRunning = true;

	public void run() {
		while (menuRunning) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				// do purchase


				while (purchaseMenuRunning) {
					String choiceTwo = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

					if (choiceTwo.equals(PURCHASE_MENU_FINISH_TRANSACTION)) {
						purchaseMenuRunning = false;

					}

				}
			}else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
					menuRunning = false;
				}
			}
		}


	public static void main(String[] args) throws FileNotFoundException {

		VendingMachine vendingMachine = new VendingMachine();

		vendingMachine.stockVendingMachine();

		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();



	}







//	String choiceTwo = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
//	while {String choiceTwo = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
//			if (!choiceTwo.equals(PURCHASE_MENU_FINISH_TRANSACTION))
//
//	}




	}




