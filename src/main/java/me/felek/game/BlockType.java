package me.felek.game;

import me.felek.game.utils.drawUtils.Color;

public class BlockType {
    private Color color = new Color(0, 0, 0);

    public BlockType(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
