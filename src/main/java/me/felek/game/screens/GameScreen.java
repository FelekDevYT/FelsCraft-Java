package me.felek.game.screens;

import me.felek.Main;
import me.felek.game.Game;
import me.felek.game.LevelSO;
import me.felek.game.listeners.KeyListener;
import me.felek.game.listeners.MouseListener;
import me.felek.game.listeners.MouseWheelListener;
import me.felek.game.managers.InventoryManager;
import me.felek.game.managers.ModManager;
import me.felek.game.modding.luaAPI.event.EventVal;
import me.felek.lib.logUtils.LogLevel;
import me.felek.lib.logUtils.Logger;

import javax.swing.*;
import java.awt.*;

public class GameScreen{
    private JFrame frame = new JFrame();
    public static JPanel gamePanel = new JPanel() {// so bad, but not in Main.java lol :)
        public void paint(Graphics g){
            Game.world.renderWorld();

//            Game.overlay.draw(g);

            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 15));
            g.drawString("@by " + Game.AUTHOR, 1140,670);
            g.drawString(Game.FULL_NAME, 10, 15);
        }

        public void redraw(){
            repaint();
        }
    };

    public GameScreen() {
        Logger.log(LogLevel.INFO, "Opening world.");
        LevelSO.loadLevel("level0");
        Logger.log(LogLevel.OK, "World successfully loaded.");

        Game.init(new Main());
        InventoryManager.init();

        ModManager.setupAll();

        Logger.log(LogLevel.INFO, "Opening GUI.");
        frame.getContentPane().add(gamePanel);
        frame.setTitle(Game.FULL_NAME);
        frame.setSize(Game.SCREEN_WIDTH - 3, (Game.SCREEN_HEIGHT + Game.INVENTORY_HEIGHT));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(new KeyListener());//port this stuff
        frame.addMouseListener(new MouseListener());//and this also

//        frame.addMouseListener(new EventVal());//and this lol

        frame.addMouseWheelListener(new MouseWheelListener());//this!
        Logger.log(LogLevel.OK, "GUI opened, game has been started.");

        frame.setVisible(true);
    }
}
