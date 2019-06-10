package com.adevinta.searchengine.display;

/**
 * This class used to show messages in console.
 * Made it as singleton so one display object will be used.
 */
public class ConsoleDisplay implements IDisplay {

    private static ConsoleDisplay instance = new ConsoleDisplay();

    private ConsoleDisplay()
    {

    }

    public static ConsoleDisplay getInstance()
    {
        return instance;
    }


    @Override
    public void printMessage(String msg) {
        System.out.println(msg);
    }

    @Override
    public void printError(String err) {

        //Currently just displaying as simple message
        printMessage("ERROR!!! "+err);

    }
}
