package me.felek.game.utils.drawUtils;

import com.raylib.Raylib;

import static com.raylib.Raylib.*;

public class DrawTool {
    public static void drawFilledRectangle(Raylib.Color color, int x, int y, int w, int h) {
        DrawRectangle( x, y, w, h, color);
    }

    public static void drawRect(Raylib.Color color, int x, int y, int w, int h) {
        DrawRectangleLines( x, y, w, h, color);
    }
}
