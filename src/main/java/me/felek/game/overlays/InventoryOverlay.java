package me.felek.game.overlays;

import me.felek.game.BlockType;
import me.felek.game.Game;
import me.felek.game.managers.InventoryManager;

import java.awt.*;

public class InventoryOverlay {
    private int x;
    private int y;
    private Color color;

    public InventoryOverlay(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public void draw(Graphics g) {
        Color previous = g.getColor();
        g.setColor(color);
        g.fillRect(x, y, Game.SCREEN_WIDTH, Game.INVENTORY_HEIGHT);

        drawInventory(g);

        g.setColor(previous);
    }

    private void drawSelectedInventorySlot(Graphics g, int xb, int yb, BlockType type) {
        Color previous = g.getColor();

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(type.getColor());
        g2d.fillRect(xb, yb, InventoryManager.INVENTORY_BLOCK_SIZE, InventoryManager.INVENTORY_BLOCK_SIZE);
        g2d.setColor(Color.GRAY);
        g2d.setStroke(new BasicStroke(5.0f));
        g2d.drawRect(xb, yb, InventoryManager.INVENTORY_BLOCK_SIZE, InventoryManager.INVENTORY_BLOCK_SIZE);

        g.setColor(previous);
    }

    private void drawInventory(Graphics g) {
        Color previous = g.getColor();

        int start = Game.SCREEN_WIDTH / 3;

        drawBlockInInventory(g, start, (Game.SCREEN_HEIGHT + Game.INVENTORY_HEIGHT) - 90, InventoryManager.inventory[0]);
        for(int i = 1; i < 9; i++){
            drawBlockInInventory(g,start + (InventoryManager.INVENTORY_BLOCK_SIZE * i), (Game.SCREEN_HEIGHT + Game.INVENTORY_HEIGHT) - 90, InventoryManager.inventory[i]);
        }
        drawSelectedInventorySlot(g,start + (InventoryManager.INVENTORY_BLOCK_SIZE * InventoryManager.current_slot), (Game.SCREEN_HEIGHT + Game.INVENTORY_HEIGHT) - 90, InventoryManager.inventory[InventoryManager.current_slot]);

        g.setColor(previous);
    }

    private void drawBlockInInventory(Graphics g, int xb, int yb, BlockType type) {
        Color previous = g.getColor();

        g.setColor(type.getColor());
        g.fillRect(xb, yb, InventoryManager.INVENTORY_BLOCK_SIZE, InventoryManager.INVENTORY_BLOCK_SIZE);
        g.setColor(Color.BLACK);
        g.drawRect(xb, yb, InventoryManager.INVENTORY_BLOCK_SIZE, InventoryManager.INVENTORY_BLOCK_SIZE);

        g.setColor(previous);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
