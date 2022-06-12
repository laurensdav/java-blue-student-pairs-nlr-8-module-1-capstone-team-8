package com.techelevator.view;

import java.io.InputStream;
import java.io.OutputStream;

public class HiddenMenu extends Menu {

    @Override
    public void displayMenuOptions(Object[] options) {
        out.println();
        for (int i = 0; i < options.length-1; i++) {
            int optionNum = i + 1;
            out.println(optionNum + ") " + options[i]);
        }
        out.print(System.lineSeparator() + "Please choose an option >>> ");
        out.flush();
    }

    public HiddenMenu(InputStream input, OutputStream output) {
        super(input, output);
    }



}
