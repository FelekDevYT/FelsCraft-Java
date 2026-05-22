package me.felek.game.modding.luaAPI.event;

import me.felek.game.Block;
import me.felek.game.BlockType;
import me.felek.game.Game;
import me.felek.game.managers.BlockManager;
import me.felek.game.managers.InventoryManager;
import me.felek.game.modding.*;

import static com.raylib.Raylib.*;

public class EventVal {

    public void pollEvents() {
        pollMouse();
        pollKeyboard();
        pollGameEvents();
    }

    private void pollGameEvents() {
        switch (GetCharPressed()) {
            case 'a':
            case 'A':
                EventVal.callPlayerMovedEvent(Game.player.getX(), Game.player.getY(), -1, 0);
                Game.player.move(-1, 0);
                break;
            case 'd':
            case 'D':
                EventVal.callPlayerMovedEvent(Game.player.getX(), Game.player.getY(), 1, 0);
                Game.player.move(1, 0);
                break;
            case 's':
            case 'S':
                EventVal.callPlayerMovedEvent(Game.player.getX(), Game.player.getY(), 0, 1);
                Game.player.move(0, 1);
                break;
            case 'w':
            case 'W':
                EventVal.callPlayerMovedEvent(Game.player.getX(), Game.player.getY(), 0, -1);
                Game.player.move(0, -1);
                break;
        }

        int x = GetMouseX();
        int y = GetMouseY();

        int cubeX = x / Game.BLOCK_SIZE;
        int cubeY = y / Game.BLOCK_SIZE ;

        if (cubeX < 0 || cubeX >= Game.world.blocks.length ||
                cubeY < 0 || cubeY >= Game.world.blocks[0].length) {
            return;
        }

        Block currentBlock = Game.world.blocks[cubeX][cubeY];
        String oldType = BlockManager.getBlockNameAsBlockType(currentBlock.getType());

        if (IsMouseButtonPressed(MOUSE_BUTTON_LEFT)) {
            BlockType skyType = BlockManager.getBlockTypeAsName("sky");
            Game.world.blocks[cubeX][cubeY] = new Block(
                    cubeX * Game.BLOCK_SIZE,
                    cubeY * Game.BLOCK_SIZE,
                    skyType
            );
            EventVal.callBlockBroken(oldType, cubeX, cubeY);
        }
        else if (IsMouseButtonPressed(MOUSE_BUTTON_RIGHT)) {
            BlockType newType = InventoryManager.getItemAtPos(InventoryManager.current_slot);
            String newTypeName = BlockManager.getBlockNameAsBlockType(newType);
            Game.world.blocks[cubeX][cubeY] = new Block(
                    cubeX * Game.BLOCK_SIZE,
                    cubeY * Game.BLOCK_SIZE,
                    newType
            );
            EventVal.callBlockPlaced(oldType, newTypeName, cubeX, cubeY);
        }

        if (GetMouseWheelMove() == 1.0) {
            InventoryManager.scroll(false);
        } else if (GetMouseWheelMove() == -1.0) {
            InventoryManager.scroll(true);
        }
    }

    private void pollMouse() {
        int mouseX = GetMouseX();
        int mouseY = GetMouseY();

        if (IsMouseButtonPressed(MOUSE_BUTTON_LEFT)) {
            EventBus.call("mouse.pressed", "left", mouseX, mouseY);
        }
        if (IsMouseButtonPressed(MOUSE_BUTTON_RIGHT)) {
            EventBus.call("mouse.pressed", "right", mouseX, mouseY);
        }

        if (IsMouseButtonReleased(MOUSE_BUTTON_LEFT)) {
            EventBus.call("mouse.released", "left", mouseX, mouseY);
        }
        if (IsMouseButtonReleased(MOUSE_BUTTON_RIGHT)) {
            EventBus.call("mouse.released", "right", mouseX, mouseY);
        }

        if (IsMouseButtonReleased(MOUSE_BUTTON_LEFT)) {
            EventBus.call("mouse.clicked", "left", mouseX, mouseY);
        }
    }

    private void pollKeyboard() {
        if (GetKeyPressed() != 0) {
            EventBus.call("keyboard.keyPressed", GetKeyPressed());
        }
    }


    //====================================================
    //=================WORLD LISTENERS====================
    //====================================================

    public static void callBlockPlaced(String oldBlockType, String newBlockType, int posX, int posY) {
        EventBus.call("world.block_placed", oldBlockType, newBlockType, posX, posY);
    }

    public static void callBlockBroken(String blockType, int posX, int posY) {
        EventBus.call("world.block_broken", blockType, posX, posY);
    }

    public static void callPlayerMovedEvent(int oldX, int oldY, int dx, int dy) {
        EventBus.call("player.moved", oldX, oldY, dx, dy);
    }
}
