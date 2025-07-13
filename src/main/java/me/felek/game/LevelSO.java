package me.felek.game;

import me.felek.game.managers.BlockManager;
import me.felek.game.managers.LevelManager;
import me.felek.game.utils.JSONParser;
import me.felek.lib.logUtils.LogLevel;
import me.felek.lib.logUtils.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/*
Signature of level(version linked to world version):
<blockX> <blockY> <blockTypeAsString>
 */
public class LevelSO {
    public static void saveLevel(String level) {
        Logger.log(LogLevel.INFO, "Saving level: " + String.format("worlds/%s/data/%s.fsw", Game.worldName, level));
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(String.format("worlds/%s/data/%s.fsw", Game.worldName, level)))) {
            for (int x = 0; x < Game.GAME_WIDTH; x++) {
                for (int y = 0; y < Game.GAME_HEIGHT; y++) {
                    Block b = Game.world.getBlockAt(x, y);
                    bw.write(String.format("%d %d %s\n", x, y, b.getType()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(BufferedWriter bw = Files.newBufferedWriter(Paths.get(String.format("worlds/%s/player.json", Game.worldName)))){
            bw.write("{\n");
            bw.write("  \"level\": " + LevelManager.levelPointer + ",\n");
            bw.write("  \"posX\": " + Game.player.getX() + ",\n");
            bw.write("  \"posY\": " + Game.player.getY() + "\n");
            bw.write("}");
        }catch (IOException exc){
            exc.printStackTrace();
        }

        Logger.log(LogLevel.OK, "Level saved.");
    }

    public static void loadLevel(String level) {
        Logger.log(LogLevel.INFO, "Loading level: " + String.format("worlds/%s/data/%s.fsw", Game.worldName, level));
        try {
            List<String> lines = Files.readAllLines(Paths.get(String.format("worlds/%s/data/%s.fsw", Game.worldName, level)));

            for (String line : lines) {
                String[] split = line.split(" ");
                int x = Integer.parseInt(split[0]);
                int y = Integer.parseInt(split[1]);
                BlockType type = BlockManager.getBlockTypeAsName(split[2]);

                if (x >= 0 && x < Game.GAME_WIDTH && y >= 0 && y < Game.GAME_HEIGHT) {
                    Game.world.blocks[x][y] = new Block(
                            x * Game.BLOCK_SIZE,
                            y * Game.BLOCK_SIZE,
                            type
                    );
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }

        try{
           String text = Files.readString(Path.of(String.format("worlds/%s/player.json", Game.worldName)));

           Game.player.moveTo(JSONParser.parseInt(text, "posX"), JSONParser.parseInt(text, "posY"));
           LevelManager.levelPointer = JSONParser.parseInt(text, "level");
        }catch (IOException exc){
            exc.printStackTrace();
        }

        Logger.log(LogLevel.OK, "Level loaded.");
    }
}