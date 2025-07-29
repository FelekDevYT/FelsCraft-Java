package me.felek.game.processes;

import me.felek.game.Game;
import me.felek.game.LevelSO;
import me.felek.game.managers.LevelManager;
import me.felek.lib.logUtils.LogLevel;
import me.felek.lib.logUtils.Logger;

import java.io.File;

public class WorldProcess implements Process {
    private void incOrDecLevel(boolean movingLeft) {
        if (movingLeft) {
            LevelManager.levelPointer--;
        } else {
            LevelManager.levelPointer++;
        }
    }

    @Override
    public void process() {
        if (Game.player.getX() < 0 || Game.player.getX() >= Game.GAME_WIDTH) {
            boolean movingLeft = Game.player.getX() < 0;
            int currentLevel = LevelManager.levelPointer;

            incOrDecLevel(movingLeft);
            int newLevel = LevelManager.levelPointer;

            LevelSO.saveLevel("level" + currentLevel);

            String newLevelPath = String.format("worlds/%s/data/level%d.fsw",
                    Game.worldName, newLevel);

            if (new File(newLevelPath).exists()) {
                LevelSO.loadLevel("level" + newLevel);
            } else {
                Game.world.generateBlocks();
                LevelSO.saveLevel("level" + newLevel);
            }

            if (movingLeft) {
                Game.player.moveTo(Game.GAME_WIDTH - 3, Game.world.getMaxY(1));
            } else {
                Game.player.moveTo(1, Game.world.getMaxY(1));
            }

            Logger.log(LogLevel.INFO, "Moving to level " + newLevel);
        }
    }
}
