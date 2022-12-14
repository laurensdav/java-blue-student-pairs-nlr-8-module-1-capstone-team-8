package com.techelevator.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

	protected PrintWriter out;
	protected Scanner in;

	public Menu(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output);
		this.in = new Scanner(input);
	}

	// Purchase methods
	public BigDecimal getMoney() {
		BigDecimal insertedMoney = BigDecimal.valueOf(0);

		try {
			insertedMoney = in.nextBigDecimal();
			in.nextLine();
			insertedMoney.intValueExact();
		} catch (ArithmeticException e) {
			insertedMoney = BigDecimal.valueOf(0);
			System.out.println("Error: only dollars accepted");
		} catch (InputMismatchException e) {
			System.out.println("Error: insert valid currency");
		}
		return  insertedMoney;
	}

	public String getItemCode() {
		return in.nextLine();
	}

	// Menu methods
	public Object getChoiceFromOptions(Object[] options) {
		Object choice = null;
		while (choice == null) {
			displayMenuOptions(options);
			choice = getChoiceFromUserInput(options);
		}
		return choice;
	}

	private Object getChoiceFromUserInput(Object[] options) {
		Object choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if (selectedOption > 0 && selectedOption <= options.length) {
				choice = options[selectedOption - 1];
			}
		} catch (NumberFormatException e) {
			// eat the exception, an error message will be displayed below since choice will be null
		}
		if (choice == null) {
			out.println(System.lineSeparator() + "*** " + userInput + " is not a valid option ***" + System.lineSeparator());
		}
		return choice;
	}

	protected void displayMenuOptions(Object[] options) {
		out.println();
		for (int i = 0; i < options.length; i++) {
			int optionNum = i + 1;
			out.println(optionNum + ") " + options[i]);
		}
		out.print(System.lineSeparator() + "Please choose an option >>> ");
		out.flush();
	}
}
