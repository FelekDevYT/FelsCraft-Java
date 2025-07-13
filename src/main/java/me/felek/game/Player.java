package me.felek.game;

import me.felek.game.managers.BlockManager;

import java.awt.*;

public class Player {
    private int x;
    private int y;

    public Player(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        Block p = new Block((Game.BLOCK_SIZE * x), (Game.BLOCK_SIZE * y), BlockManager.getBlockTypeAsName("player"));
        p.draw(g);
    }

    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }

    public void moveTo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void moveX(int dx) {
        this.x += dx;
    }

    public void moveY(int dy) {
        this.y += dy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
