package me.felek.game;

import me.felek.Main;
import me.felek.game.listeners.KeyListener;
import me.felek.game.processes.PhysicsProcess;
import me.felek.game.processes.Process;
import me.felek.lib.utils.MathUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game {
    // COPYRIGHTS
    public static final String AUTHOR = "FelekDevYT";
    public static final String VERSION = "0.1";
    public static final String NAME = "FelsCraft";
    public static final String FULL_NAME = NAME + " " + VERSION;

    // GAME SETTINGS
    public static final int BLOCK_SIZE = 20;
    public static final int GAME_WIDTH = Game.SCREEN_WIDTH /Game.BLOCK_SIZE;//count in blocks!!!
    public static final int GAME_HEIGHT = Game.SCREEN_HEIGHT /Game.BLOCK_SIZE;
    public static final int SCREEN_WIDTH = 1280;//it is count of screen size
    public static final int SCREEN_HEIGHT = 720;
    public static final int FPS = 10;
    public static final Process[] processes = new Process[1];

    // GENERATION VARS
    public static final OreLevel ironLevel = new OreLevel(5, 15, new int[]{1, 11, 1, 11});
    public static final OreLevel coalLevel = new OreLevel(5, 15, new int[]{1, 10, 1, 10});
    public static final OreLevel diamondLevel = new OreLevel(1, 7, new int[]{1, 15, 1, 15});
    public static World world = new World();

    //ANOTHER
    public static final Player player = new Player(1, 1);
    public static Main INSTANCE;
    public static Timer gameUpdater;

    //WORLD INFOS
    public static String worldName;
    public static String worldVersion;

    public static void init(Main m) {
        INSTANCE = m;

        processes[0] = new PhysicsProcess();

        gameUpdater = new Timer(FPS, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(Process p : processes){
                    p.process();
                }
                INSTANCE.redraw();
            }
        });
        gameUpdater.start();
    }
}
