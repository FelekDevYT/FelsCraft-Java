package me.felek.game;

import java.awt.*;

public enum BlockType {
    GRASS(new Color(48, 197, 48)),
    STONE(Color.GRAY),
    IRON(new Color(117, 111, 102)),
    SKY(new Color(7, 139, 250)),
    COAL(new Color(60, 60, 60)),
    DIAMOND(new Color(11, 135, 150)),
    PLAYER(Color.ORANGE);

    private Color c;
    BlockType(Color c){
        this.c = c;
    }

    public Color getColor(){
        return c;
    }
}
