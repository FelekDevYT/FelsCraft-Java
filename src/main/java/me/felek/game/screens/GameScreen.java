package me.felek.game.screens;

import me.felek.Main;
import me.felek.game.Block;
import me.felek.game.BlockType;
import me.felek.game.Game;
import me.felek.game.LevelSO;
import me.felek.game.lang.LangHandler;
import me.felek.game.lang.Language;
import me.felek.game.listeners.KeyListener;
import me.felek.game.listeners.MouseListener;
import me.felek.game.listeners.MouseWheelListener;
import me.felek.game.managers.InventoryManager;
import me.felek.game.worldgen.Grid;
import me.felek.game.worldgen.TreeStructure;
import me.felek.lib.logUtils.LogLevel;
import me.felek.lib.logUtils.Logger;

import javax.swing.*;

public class GameScreen{
    private JFrame frame = new JFrame();

    public GameScreen() {
        Logger.log(LogLevel.INFO, "Opening world.");
        LevelSO.loadLevel("level0");
        Logger.log(LogLevel.OK, "World successfully loaded.");

        Main m = new Main();

        Game.init(m);
        InventoryManager.init();

        Logger.log(LogLevel.INFO, "Opening GUI.");
        frame.getContentPane().add(m);
        frame.setTitle(Game.FULL_NAME);
        frame.setSize(Game.SCREEN_WIDTH - 3, (Game.SCREEN_HEIGHT + Game.INVENTORY_HEIGHT));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(new KeyListener());
        frame.addMouseListener(new MouseListener());
        frame.addMouseWheelListener(new MouseWheelListener());
        Logger.log(LogLevel.OK, "GUI opened, game has been started.");

        frame.setVisible(true);
    }
}
