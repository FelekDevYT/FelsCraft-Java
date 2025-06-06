package me.felek.game.screens;

import me.felek.Main;
import me.felek.game.Game;
import me.felek.game.LevelSO;
import me.felek.game.lang.LangHandler;
import me.felek.game.lang.Language;
import me.felek.game.listeners.KeyListener;
import me.felek.lib.logUtils.LogLevel;
import me.felek.lib.logUtils.Logger;

import javax.swing.*;

public class GameScreen{
    private JFrame frame = new JFrame();

    public GameScreen() {
        Logger.log(LogLevel.INFO, "Opening world.");
        LevelSO.loadLevel("level");
        Logger.log(LogLevel.OK, "World successfully loaded.");

        Main m = new Main();

        Game.init(m);

        Logger.log(LogLevel.INFO, "Opening GUI.");
        frame.getContentPane().add(m);
        frame.setTitle(Game.FULL_NAME);
        frame.setSize(Game.SCREEN_WIDTH - 3, Game.SCREEN_HEIGHT);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(new KeyListener());
        Logger.log(LogLevel.OK, "GUI opened, game has been started.");

        frame.setVisible(true);
    }
}
