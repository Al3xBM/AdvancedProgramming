package com;


import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class Info {
    private String[] info;
    private String country;
    private String language;
    private String currency;
    private String weekdays;
    private String months;
    private String today;

    public Info(Locale locale){
        ResourceBundle bundle = ResourceBundle.getBundle("res.Message", new Locale(locale.getISO3Language(), locale.getISO3Country()));

        info = bundle.getString("info").split("\\|");

        setInfo();
    }

    private void setInfo(){
        country = info[0];
        language = info[1];
        currency = info[2];
        weekdays = info[3];
        months = info[4];
        today = info[5];
    }

    public void displayInfo(){
        for( String s : info ){
            System.out.println(s);
        }
    }

    public String getCountry() {
        return country;
    }


    public String getLanguage() {
        return language;
    }


    public String getCurrency() {
        return currency;
    }


    public String getWeekdays() {
        return weekdays;
    }


    public String getMonths() {
        return months;
    }


    public String getToday() {
        return today;
    }

}
