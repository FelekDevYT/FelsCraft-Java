package me.felek.game.managers;

import me.felek.game.BlockType;

public class InventoryManager {
    public static final int INVENTORY_BLOCK_SIZE = 40;
    public static int current_slot = 0;

    public static BlockType[] inventory;

    public static void init() {
        inventory = new BlockType[9];
        inventory[0] = BlockType.GRASS;
        inventory[1] = BlockType.STONE;
        inventory[2] = BlockType.IRON;
        inventory[3] = BlockType.COAL;
        inventory[4] = BlockType.DIAMOND;
        for(int i = 5; i < 9;i++){
            inventory[i] = BlockType.SKY;
        }
    }
}
