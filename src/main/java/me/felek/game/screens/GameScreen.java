package me.felek.game.screens;

import me.felek.Main;
import me.felek.game.Game;
import me.felek.game.LevelSO;
import me.felek.game.lang.LangHandler;
import me.felek.game.lang.Language;
import me.felek.game.listeners.KeyListener;

import javax.swing.*;

public class GameScreen{
    private JFrame frame = new JFrame();

    public GameScreen() {
        LevelSO.loadLevel("level");

        Main m = new Main();

        Game.init(m);

        frame.getContentPane().add(m);
        frame.setTitle(Game.FULL_NAME);
        frame.setSize(Game.SCREEN_WIDTH - 3, Game.SCREEN_HEIGHT);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(new KeyListener());

        frame.setVisible(true);
    }
}
