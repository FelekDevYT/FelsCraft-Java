package me.felek.game.processes;

import me.felek.game.Game;
import me.felek.game.managers.BlockManager;

public class PhysicsProcess implements Process {

    @Override
    public void process() {
        int playerX = Game.player.getX();
        int playerY = Game.player.getY();

        if(Game.world.getBlockAt(playerX, playerY + 1).getType() == BlockManager.getBlockTypeAsName("sky")){
            Game.player.move(0, 1);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
