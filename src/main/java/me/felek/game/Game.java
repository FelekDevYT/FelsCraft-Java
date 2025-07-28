package me.felek.game;

import me.felek.Main;
import me.felek.game.managers.BlockManager;
import me.felek.game.managers.ModManager;
import me.felek.game.overlays.InventoryOverlay;
import me.felek.game.processes.PhysicsProcess;
import me.felek.game.processes.Process;
import me.felek.game.processes.WorldProcess;
import me.felek.game.worldgen.oregen.OreLevel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game {
    // COPYRIGHTS
    public static final String AUTHOR = "FelekDevYT";
    public static final String VERSION = "0.8";
    public static final String NAME = "FelsCraft";
    public static final String FULL_NAME = NAME + " " + VERSION;

    // GAME SETTINGS
    public static final int BLOCK_SIZE = 20;
    public static final int GAME_WIDTH = Game.SCREEN_WIDTH / Game.BLOCK_SIZE;//count in blocks!!!
    public static final int GAME_HEIGHT = Game.SCREEN_HEIGHT / Game.BLOCK_SIZE;
    public static final int SCREEN_WIDTH = 1280;//it is count of screen size
    public static final int SCREEN_HEIGHT = 720;
    public static final int FPS = 10;
    public static final Process[] processes = new Process[2];

    //INVENTORY
    public static final int INVENTORY_HEIGHT = 100;
    public static final InventoryOverlay overlay = new InventoryOverlay(0, SCREEN_HEIGHT, Color.BLACK);

    // GENERATION VARS
    public static final OreLevel ironLevel = new OreLevel(5, 17, new int[]{1, 14, 5, 14});
    public static final OreLevel coalLevel = new OreLevel(7, 17, new int[]{1, 10, 1, 10});
    public static final OreLevel diamondLevel = new OreLevel(1, 10, new int[]{1, 15, 1, 15});
    public static World world = new World();

    //ANOTHER
    public static final Player player = new Player(0, 0);

    public static Main INSTANCE;
    public static Timer gameUpdater;

    //WORLD INFOS
    public static String worldName;
    public static String worldVersion;

    public static void init(Main m) {
        INSTANCE = m;

        processes[0] = new WorldProcess();
        processes[1] = new PhysicsProcess();

        gameUpdater = new Timer(FPS, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ModManager.updateAll();

                for(Process p : processes){
                    p.process();
                }
                INSTANCE.redraw();
            }
        });
        gameUpdater.start();
    }
}
