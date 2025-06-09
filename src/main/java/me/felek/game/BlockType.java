package me.felek.game;

import java.awt.*;

public enum BlockType {
    GRASS(new Color(42, 147, 42)),
    STONE(Color.GRAY),
    IRON(new Color(117, 111, 102)),
    SKY(new Color(7, 139, 250)),
    COAL(new Color(60, 60, 60)),
    DIAMOND(new Color(11, 135, 150)),
    PLAYER(Color.ORANGE),
    LEAVES(new Color(33, 224, 33)),
    LOG(new Color(99, 61, 32)),
    DIRT(new Color(59, 39, 23)),;

    private Color c;
    BlockType(Color c){
        this.c = c;
    }

    public Color getColor(){
        return c;
    }
}
