package me.felek.lib.logUtils;

import me.felek.lib.consoleUtils.ConsoleColor;

public enum LogLevel {
    DEBUG(ConsoleColor.PURPLE, "DEBUG"),
    ERROR(ConsoleColor.RED, "ERROR"),
    INFO(ConsoleColor.BLUE, "INFO"),
    OK(ConsoleColor.GREEN, "OK");

    public ConsoleColor col;
    public String level;
    LogLevel(ConsoleColor c, String l){
        col = c;
        level = l;
    }
}
