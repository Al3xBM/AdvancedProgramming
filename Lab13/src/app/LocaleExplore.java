package app;

import com.DisplayLocales;
import com.Info;
import com.SetLocale;

import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleExplore {
    public static void main(String[] args){
        DisplayLocales display = new DisplayLocales();
        System.out.println(Arrays.toString(display.getAvailable()).replaceAll("\\[,|\\]", ""));
        SetLocale localeSet = new SetLocale(new Locale("ro", "RO"));
        Info info = new Info(Locale.getDefault());
        info.displayInfo();
    }
}
