package me.felek.game.listeners;

import me.felek.game.Block;
import me.felek.game.BlockType;
import me.felek.game.Game;
import me.felek.game.managers.BlockManager;
import me.felek.game.managers.InventoryManager;
import me.felek.game.modding.luaAPI.event.EventVal;

import java.awt.event.MouseEvent;

public class MouseListener implements java.awt.event.MouseListener {
    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY() - 14;

        int cubeX = x / Game.BLOCK_SIZE;
        int cubeY = y / Game.BLOCK_SIZE - 1;

        if (cubeX < 0 || cubeX >= Game.world.blocks.length ||
                cubeY < 0 || cubeY >= Game.world.blocks[0].length) {
            return;
        }

        Block currentBlock = Game.world.blocks[cubeX][cubeY];
        String oldType = BlockManager.getBlockNameAsBlockType(currentBlock.getType());

        if (e.getButton() == MouseEvent.BUTTON1) {
            BlockType skyType = BlockManager.getBlockTypeAsName("sky");
            Game.world.blocks[cubeX][cubeY] = new Block(
                    cubeX * Game.BLOCK_SIZE,
                    cubeY * Game.BLOCK_SIZE,
                    skyType
            );
            EventVal.callBlockBroken(oldType, cubeX, cubeY);
        }
        else if (e.getButton() == MouseEvent.BUTTON3) {
            BlockType newType = InventoryManager.getItemAtPos(InventoryManager.current_slot);
            String newTypeName = BlockManager.getBlockNameAsBlockType(newType);
            Game.world.blocks[cubeX][cubeY] = new Block(
                    cubeX * Game.BLOCK_SIZE,
                    cubeY * Game.BLOCK_SIZE,
                    newType
            );
            EventVal.callBlockPlaced(oldType, newTypeName, cubeX, cubeY);
        }
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
