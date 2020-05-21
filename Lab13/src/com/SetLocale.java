package com;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.util.*;

public class SetLocale {
    Locale locale;

    public SetLocale(Locale locale){
        this.locale = locale;
        setProperties(Locale.getDefault());
        setProperties(this.locale);
    }

    private void setProperties(Locale locale){

        Properties prop = new Properties();
        String path = "C:\\Users\\aliba\\IdeaProjects\\Lab13\\src\\res\\Message_ro.properties";
        System.out.println(path);
        try {
            FileOutputStream out = new FileOutputStream(path);
            FileInputStream in = new FileInputStream(path);
            prop.load(in);
            in.close();
            prop.setProperty("prompt", "setLocale");
            prop.setProperty("locales", Arrays.toString(Locale.getAvailableLocales()).replaceAll("\\[,|\\]", ""));
            prop.setProperty("locale.set", Locale.getDefault().toString());
            prop.setProperty("info", locale.getCountry() + "|" + locale.getLanguage() + "|"
                    + Currency.getInstance(locale).getDisplayName() + "|" + Arrays.toString(DateFormatSymbols.getInstance(locale).getWeekdays()).replaceAll("\\[,|\\]", "")
                    + "|" + Arrays.toString(DateFormatSymbols.getInstance(locale).getMonths()).replaceAll("\\[,|\\]", "")  + "|" +
                    DateFormat.getDateInstance(DateFormat.MEDIUM, locale));
            prop.setProperty("invalid", "-");
            prop.store(out, null);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
