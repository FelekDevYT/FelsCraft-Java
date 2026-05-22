package me.felek.game;

import me.felek.game.utils.drawUtils.Color;
import me.felek.game.utils.drawUtils.Colors;
import me.felek.game.utils.drawUtils.DrawTool;

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

    public void draw(){
//        Color prev = graphics.getColor();
//        graphics.setColor(type.getColor());
//        graphics.fillRect(x, y, Game.BLOCK_SIZE, Game.BLOCK_SIZE);
//        graphics.setColor(Color.BLACK);
//        graphics.drawRect(x, y, Game.BLOCK_SIZE, Game.BLOCK_SIZE);
//        graphics.setColor(prev);
        DrawTool.drawFilledRectangle(type.getColor().getColor(), x, y, Game.BLOCK_SIZE, Game.BLOCK_SIZE);
        DrawTool.drawRect(Colors.BLACK.getColor(), x, y, Game.BLOCK_SIZE, Game.BLOCK_SIZE);
    }
}
