package me.felek.lib.logUtils;

import me.felek.lib.consoleUtils.ConsoleUtility;

public class Logger {
    public static void log(LogLevel l, String text){
        System.out.print("[ ");
        ConsoleUtility.colorizedPrint(l.level, l.col);
        System.out.print(" ] ");
        System.out.println(text);
    }
}
