package me.felek.game.managers;

import java.io.File;

public class LevelManager {
    public static int levelPointer = 0;

    public static void createWorldsFolder() {
        new File("worlds/").mkdirs();
    }
}
