package me.felek.game.managers;

import me.felek.game.BlockType;

public class InventoryManager {
    public static final int INVENTORY_BLOCK_SIZE = 40;
    public static int current_slot = 0;

    private static BlockType[] inventory;

    public static void setItemInPos(int pos, BlockType type) {
        inventory[pos] = type;
    }

    public static BlockType getItemAtPos(int pos) {
        return inventory[pos];
    }

    public static void addItemToInventory(BlockType type) {
        inventory[current_slot] = type;
        current_slot++;
    }

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
        current_slot = 5;
    }
}
