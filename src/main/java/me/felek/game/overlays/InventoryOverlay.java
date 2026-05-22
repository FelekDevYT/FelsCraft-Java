package me.felek.game.overlays;

import me.felek.game.BlockType;
import me.felek.game.Game;
import me.felek.game.managers.BlockManager;
import me.felek.game.managers.InventoryManager;
import me.felek.game.utils.drawUtils.Color;
import me.felek.game.utils.drawUtils.Colors;
import me.felek.game.utils.drawUtils.DrawTool;

public class InventoryOverlay {
    private Color color;

    public InventoryOverlay(Color color) {
        this.color = color;
    }

    public void draw() {
//        Color previous = g.getColor();
//        g.setColor(color);
//        g.fillRect(x, y, Game.SCREEN_WIDTH, Game.INVENTORY_HEIGHT);
        DrawTool.drawFilledRectangle(color.getColor(), 0, Game.SCREEN_HEIGHT - Game.INVENTORY_HEIGHT, Game.SCREEN_WIDTH, Game.INVENTORY_HEIGHT);

        drawInventory();
    }

    private void drawSelectedInventorySlot(int xb, int yb, BlockType type) {
        DrawTool.drawFilledRectangle(type.getColor().getColor(), xb, yb, InventoryManager.INVENTORY_BLOCK_SIZE, InventoryManager.INVENTORY_BLOCK_SIZE);
        DrawTool.drawRect(Colors.GRAY.getColor(), xb, yb, InventoryManager.INVENTORY_BLOCK_SIZE, InventoryManager.INVENTORY_BLOCK_SIZE);
    }

    private void drawInventory() {
        int start = Game.SCREEN_WIDTH / 3;

        drawBlockInInventory(start, Game.SCREEN_HEIGHT - 90, InventoryManager.getItemAtPos(0));
        for (int i = 1; i < 9; i++) {
            drawBlockInInventory(start + (InventoryManager.INVENTORY_BLOCK_SIZE * i), Game.SCREEN_HEIGHT - 90, InventoryManager.getItemAtPos(i));
        }

        drawSelectedInventorySlot(start + (InventoryManager.INVENTORY_BLOCK_SIZE * InventoryManager.current_slot), Game.SCREEN_HEIGHT - 90, InventoryManager.getItemAtPos(InventoryManager.current_slot));
    }

    private void drawBlockInInventory(int xb, int yb, BlockType type) {
        DrawTool.drawFilledRectangle(type.getColor().getColor(), xb, yb, InventoryManager.INVENTORY_BLOCK_SIZE, InventoryManager.INVENTORY_BLOCK_SIZE);
        DrawTool.drawRect(Colors.BLACK.getColor(), xb, yb, InventoryManager.INVENTORY_BLOCK_SIZE, InventoryManager.INVENTORY_BLOCK_SIZE);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
