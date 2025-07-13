package me.felek.lib.consoleUtils;

import java.util.Scanner;

public class ConsoleUtility {
    public static void colorizedPrint(String text, ConsoleColor color){
        System.out.print(color.color + text + "\033[0;0m");
        System.out.flush();
    }

    public static void colorizedPrintln(String text, ConsoleColor color){
        System.out.println(color.color + text + "\033[0;0m");
        System.out.flush();
    }

    public static void print(String text){
        System.out.print(text);
    }

    public static void println(String text){
        System.out.printf(text);
    }

    public static String readLine(){
        return new Scanner(System.in).nextLine();
    }

    public static int readInt(){
        return new Scanner(System.in).nextInt();
    }
}
