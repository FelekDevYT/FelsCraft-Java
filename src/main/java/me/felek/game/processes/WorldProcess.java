package me.felek.game.processes;

import me.felek.game.Game;
import me.felek.game.LevelSO;
import me.felek.game.managers.LevelManager;
import me.felek.lib.logUtils.LogLevel;
import me.felek.lib.logUtils.Logger;

import java.io.File;

public class WorldProcess implements Process {
    private void incOrDecLevel(){
        if(Game.player.getX() < 0){
            LevelManager.levelPointer--;
        }else{
            LevelManager.levelPointer++;
        }
    }

    @Override
    public void process() {
        if(Game.player.getX() < 0 ||
            Game.player.getX() >= Game.GAME_WIDTH){
            if(LevelManager.levelPointer == 0){
                LevelSO.saveLevel("level0");
            }else{
                LevelSO.saveLevel("level" + LevelManager.levelPointer);
            }

            if(new File(String.format("worlds/%s/data/%s.fsw", Game.worldName, ("level" + String.valueOf(Game.player.getX() < 0?LevelManager.levelPointer - 1 : LevelManager.levelPointer + 1)))).exists()){
                incOrDecLevel();
                LevelSO.loadLevel("level" + LevelManager.levelPointer);

                if(Game.player.getX() < 0)
                    Game.player.moveTo(Game.GAME_WIDTH - 3, Game.world.getMaxY(1));
                else
                    Game.player.moveTo(1, Game.world.getMaxY(1));
            }else{
                incOrDecLevel();
                Game.world.generateBlocks();
                LevelSO.saveLevel("level" + LevelManager.levelPointer);

                if(Game.player.getX() < 0)
                    Game.player.moveTo(Game.GAME_WIDTH - 3, Game.world.getMaxY(1));
                else
                    Game.player.moveTo(1, Game.world.getMaxY(1));
            }

            Logger.log(LogLevel.INFO, "Moving to level " + LevelManager.levelPointer);
        }
    }
}
