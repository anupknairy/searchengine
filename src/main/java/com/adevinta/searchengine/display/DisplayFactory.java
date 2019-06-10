package com.adevinta.searchengine.display;

public class DisplayFactory {

    public static IDisplay getDisplay(DisplayType type)
    {
        switch (type) {
            case CONSOLE:
            default:
                return  ConsoleDisplay.getInstance();
        }
    }
}
