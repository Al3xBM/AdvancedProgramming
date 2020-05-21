package com;

import java.util.Locale;

public class DisplayLocales {
    private Locale[] available;

    public DisplayLocales(){
        available = Locale.getAvailableLocales();
    }

    public Locale[] getAvailable(){
        return available;
    }


}
