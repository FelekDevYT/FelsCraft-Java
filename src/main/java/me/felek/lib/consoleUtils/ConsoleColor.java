package me.felek.lib.consoleUtils;

public enum ConsoleColor {
    RED("\033[0;31m"),
    GREEN("\033[0;32m"),
    YELLOW("\033[0;33m"),
    BLUE("\033[0;34m"),
    PURPLE("\033[0;35m"),
    CYAN("\033[0;36m"),
    WHITE("\033[0;37m");

    public final String color;
    ConsoleColor(String c){
        this.color = c;
    }
}
