package me.felek.game;

import java.awt.*;

public class Block {
    private int x;
    private int y;
    BlockType type;

    public Block(int x, int y, BlockType type) {
        this.x = x;
        this.y = y;
        this.type = type;
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

    public BlockType getType() {
        return type;
    }

    public void draw(Graphics graphics){
        Color prev = graphics.getColor();
        graphics.setColor(type.getColor());
        graphics.fillRect(x, y, Game.BLOCK_SIZE, Game.BLOCK_SIZE);
        graphics.setColor(Color.BLACK);
        graphics.drawRect(x, y, Game.BLOCK_SIZE, Game.BLOCK_SIZE);
        graphics.setColor(prev);
    }
}
