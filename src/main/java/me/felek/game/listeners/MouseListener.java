package me.felek.game.listeners;

import me.felek.game.Block;
import me.felek.game.BlockType;
import me.felek.game.Game;
import me.felek.game.managers.BlockManager;
import me.felek.game.managers.InventoryManager;

import java.awt.event.MouseEvent;

public class MouseListener implements java.awt.event.MouseListener {
    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY() - 14;

        int cubeX = x / Game.BLOCK_SIZE;
        int cubeY = y / Game.BLOCK_SIZE - 1;

        BlockType type = null;

        if(e.getButton() == MouseEvent.BUTTON1) {
            type = BlockManager.getBlockTypeAsName("sky");
        }

        Game.world.blocks[cubeX][cubeY] = new Block(cubeX * Game.BLOCK_SIZE, cubeY * Game.BLOCK_SIZE, type == BlockManager.getBlockTypeAsName("sky")?type : InventoryManager.inventory[InventoryManager.current_slot]);
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
