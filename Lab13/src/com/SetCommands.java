package com;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class SetCommands {
    private static SetCommands instance = new SetCommands();

    public SetCommands(){

    }

    public static SetCommands getInstance(){
        if( instance == null ){
            instance = new SetCommands();
        }
        return instance;
    }

    public void changeSetLocale(String name){
        Properties prop = new Properties();
        String path = "C:\\Users\\aliba\\IdeaProjects\\Lab13\\src\\res\\Commands.properties";
        System.out.println(path);
        try {
            FileOutputStream out = new FileOutputStream(path);
            FileInputStream in = new FileInputStream(path);
            prop.load(in);
            in.close();
            prop.setProperty("set-locale.command", name);

            prop.store(out, null);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void changeDisplayLocales(String name){
        Properties prop = new Properties();
        String path = "C:\\Users\\aliba\\IdeaProjects\\Lab13\\src\\res\\Commands.properties";
        System.out.println(path);
        try {
            FileOutputStream out = new FileOutputStream(path);
            FileInputStream in = new FileInputStream(path);
            prop.load(in);
            in.close();
            prop.setProperty("display-locales.command", name);

            prop.store(out, null);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void changeInfo(String name){
        Properties prop = new Properties();
        String path = "C:\\Users\\aliba\\IdeaProjects\\Lab13\\src\\res\\Commands.properties";
        System.out.println(path);
        try {
            FileOutputStream out = new FileOutputStream(path);
            FileInputStream in = new FileInputStream(path);
            prop.load(in);
            in.close();
            prop.setProperty("info.command", name);

            prop.store(out, null);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void showAvailableCommands(){
        Properties prop = new Properties();
        String path = "C:\\Users\\aliba\\IdeaProjects\\Lab13\\src\\res\\Commands.properties";
        System.out.println(path);
        try {
            prop.load(new FileReader(path));
            System.out.println("The available commands are: ");
            System.out.println("    - " + prop.getProperty("display-locales.command"));
            System.out.println("    - " + prop.getProperty("set-locale.command"));
            System.out.println("    - " + prop.getProperty("info.command"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
