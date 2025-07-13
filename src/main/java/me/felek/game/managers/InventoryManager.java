package me.felek.game.managers;

import me.felek.game.BlockType;

public class InventoryManager {
    public static final int INVENTORY_BLOCK_SIZE = 40;
    public static int current_slot = 0;

    public static BlockType[] inventory;

    public static void init() {
        inventory = new BlockType[9];
        inventory[0] = BlockManager.getBlockTypeAsName("grass");
        inventory[1] = BlockManager.getBlockTypeAsName("stone");
        inventory[2] = BlockManager.getBlockTypeAsName("iron");
        inventory[3] = BlockManager.getBlockTypeAsName("coal");
        inventory[4] = BlockManager.getBlockTypeAsName("diamond");
        for(int i = 5; i < 9;i++){
            inventory[i] = BlockManager.getBlockTypeAsName("sky");
        }
    }
}
