package me.felek.game.utils;

import me.felek.lib.logUtils.LogLevel;
import me.felek.lib.logUtils.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class IO {
    public static String readAllText(File file) {
        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int ch = 0;
            while ((ch = br.read()) != -1) {
                sb.append((char) ch);
            }
        }catch (IOException exc) {
            Logger.log(LogLevel.ERROR, exc.toString());
        }

        return sb.toString();
    }
}
