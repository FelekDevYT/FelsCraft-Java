package me.felek.game;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/*
Signature of level(version linked to world version):
<blockX> <blockY> <blockTypeAsString>
 */
public class LevelSO {
    public static void saveLevel(String level) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(String.format("worlds/%s/%s.fsw", Game.worldName, level), true))) {
            for(int x = 0; x < Game.GAME_WIDTH; x++) {
                for(int y = 0; y < Game.GAME_HEIGHT; y++) {
                    Block b = Game.world.getBlockAt(x, y);
                    bw.write(String.format("%d %d %s\n", x, y, b.getType().toString()));
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
