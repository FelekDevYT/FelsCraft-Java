package me.felek.game.worldgen;

import me.felek.game.Block;
import me.felek.game.Game;

public class Grid {
    private int weightBlocksCount;
    private int heightBlocksCount;
    private Block[][] blocks;

    public Grid(int weightBlocksCount, int heightBlocksCount) {
        this.weightBlocksCount = weightBlocksCount;
        this.heightBlocksCount = heightBlocksCount;
        this.blocks = new Block[weightBlocksCount][heightBlocksCount];
    }

    public void addBlock(int x, int y, Block block) {
        blocks[x][y] = block;
    }

    public static void unpackGridOnCords(int posX, int posY, Grid grid) {
        for (int _x = 0; _x < grid.weightBlocksCount; _x++) {
            for (int _y = 0; _y < grid.heightBlocksCount; _y++) {
                Block block = grid.blocks[_x][_y];
                if (block != null) {
                    block.setX((posX + _x) * Game.BLOCK_SIZE);
                    block.setY((posY + _y) * Game.BLOCK_SIZE);
                    Game.world.blocks[posX + _x][posY + _y] = block;
                }
            }
        }
    }
}