package me.felek.game.screens;

import jdk.jfr.Event;
import me.felek.Main;
import me.felek.game.Block;
import me.felek.game.Game;
import me.felek.game.LevelSO;
import me.felek.game.managers.BlockManager;
import me.felek.game.managers.InventoryManager;
import me.felek.game.managers.ModManager;
import me.felek.game.modding.EventBus;
import me.felek.game.modding.luaAPI.event.EventVal;
import me.felek.game.overlays.InventoryOverlay;
import me.felek.game.utils.drawUtils.Colors;
import me.felek.game.utils.drawUtils.DrawTool;
import me.felek.lib.logUtils.LogLevel;
import me.felek.lib.logUtils.Logger;

import java.awt.event.MouseEvent;
import java.util.Timer;

import static com.raylib.Colors.*;
import static com.raylib.Raylib.*;

public class GameRScreen {
    private EventVal eventVal = new EventVal();

    public void init() {
        // game logic
        Logger.log(LogLevel.INFO, "Opening world.");
        LevelSO.loadLevel("level0");
        Logger.log(LogLevel.OK, "World successfully loaded.");

        //smth
        InventoryManager.init();
        ModManager.setupAll();
        Logger.log(LogLevel.OK, "GUI opened, game has been started.");
        Game.init(new Main());

        // window logic
        InitWindow(Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT, Game.FULL_NAME);
        SetTargetFPS(60);
    }

    public void update() {
        ModManager.updateAll();
        Game.processesManager.updateAll();

        eventVal.pollEvents();

        BeginDrawing();
            ClearBackground(BLACK);
            Game.world.renderWorld();
            Game.overlay.draw();
            DrawText("@by" + Game.AUTHOR, 1140, 670, 15, WHITE);
        EndDrawing();
    }

    public boolean check() {
        return !WindowShouldClose();
    }

    public void close() {
        CloseWindow();
    }
}
