package me.felek;

import me.felek.game.lang.LangHandler;
import me.felek.game.lang.Language;
import me.felek.game.managers.LevelManager;
import me.felek.game.managers.ModManager;
import me.felek.game.screens.MainMenu;
import me.felek.lib.logUtils.LogLevel;
import me.felek.lib.logUtils.Logger;

import javax.swing.*;

public class Main {
    public static final JFrame frame = new JFrame();

    public static void main(String[] args) {
        LevelManager.createWorldsFolder();

        Logger.log(LogLevel.INFO, "Setting up language.");
        LangHandler.loadLanguage(Language.en_US);
        Logger.log(LogLevel.OK, "Language loaded.");
        ModManager.initModManager();
        ModManager.loadMods();
        new MainMenu();
    }
}