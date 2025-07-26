package me.felek.game;

import me.felek.game.managers.BlockManager;
import me.felek.game.worldgen.Grid;
import me.felek.game.worldgen.TreeStructure;
import me.felek.lib.utils.MathUtils;

import java.awt.*;

import static me.felek.game.Game.*;

public class World {
    public static final String WORLD_VERSION = "0.7";

    public Block[][] blocks = new Block[GAME_WIDTH][GAME_HEIGHT];
    public int[] heights_level = new int[GAME_WIDTH];

    public int getMaxY(int x){
        for(int y = 0; y < GAME_HEIGHT; y++){
            if(blocks[x][y].getType() != BlockManager.getBlockTypeAsName("sky")){
                return y - 1;
            }
        }

        return -1;
    }

    public Block getBlockAt(int x, int y) {
        return blocks[x][y];
    }

    public void setBlock( Block block) {
        blocks[block.getX()][block.getY()] = block;
    }

    /*
    #@#
    @@@
    #|#
    #&#(|)
     */
    private void isShouldGenerateTree(int x, int y) {
        if (x - 1 < 0 || x + 1 >= GAME_WIDTH || y - 1 < 0) {
            return;
        }
        if (blocks[x + 1][y].getType() == BlockManager.getBlockTypeAsName("sky") && blocks[x - 1][y].getType() == BlockManager.getBlockTypeAsName("sky")) {
            if (blocks[x + 1][y - 1].getType() == BlockManager.getBlockTypeAsName("sky") && blocks[x - 1][y - 1].getType() == BlockManager.getBlockTypeAsName("sky")) {
                if (new TreeStructure().getRarity().isShouldGenerate()) {
                    Grid.unpackGridOnCords(x, (y - 3), new TreeStructure().getGrid());
                }
            }
        }
    }

    public void generateBlocks() {
        for (int x = 0; x < GAME_WIDTH; x++) {
            heights_level[x] = MathUtils.random(20, 25);
            if (x != 0) {
                if (Math.abs(heights_level[x] - heights_level[x - 1]) > 1) {
                    while (Math.abs(heights_level[x] - heights_level[x - 1]) > 1) {
                        heights_level[x] = MathUtils.random(20, 20 + Math.abs(heights_level[x] - heights_level[x - 1]));
                    }
                }
            }
        }

        for (int x = 0; x < GAME_WIDTH; x++) {
            for (int y = 0; y < GAME_HEIGHT; y++) {
                if (coalLevel.isShouldGenerate(Utility.reverseIndex(y))) {
                    blocks[x][y] = new Block((BLOCK_SIZE * x), (BLOCK_SIZE * y), BlockManager.getBlockTypeAsName("coal"));
                } else if (ironLevel.isShouldGenerate(Utility.reverseIndex(y))) {
                    blocks[x][y] = new Block((BLOCK_SIZE * x), (BLOCK_SIZE * y), BlockManager.getBlockTypeAsName("iron"));
                } else if (diamondLevel.isShouldGenerate(Utility.reverseIndex(y))) {
                    blocks[x][y] = new Block((BLOCK_SIZE * x), (BLOCK_SIZE * y), BlockManager.getBlockTypeAsName("diamond"));
                } else if (y < Utility.reverseIndex(heights_level[x])) {
                    blocks[x][y] = new Block((BLOCK_SIZE * x), (BLOCK_SIZE * y), BlockManager.getBlockTypeAsName("sky"));
                } else if (y < Utility.reverseIndex(heights_level[x] - 2)) {
                    blocks[x][y] = new Block((BLOCK_SIZE * x), (BLOCK_SIZE * y), BlockManager.getBlockTypeAsName("grass"));
                } else if (y < Utility.reverseIndex(heights_level[x] - 4)) {
                    blocks[x][y] = new Block((BLOCK_SIZE * x), (BLOCK_SIZE * y), BlockManager.getBlockTypeAsName("dirt"));
                } else {
                    blocks[x][y] = new Block((BLOCK_SIZE * x), (BLOCK_SIZE * y), BlockManager.getBlockTypeAsName("stone"));
                }
            }
        }

        for (int x = 1; x < GAME_WIDTH - 1; x++) {
            for (int y = 1; y < GAME_HEIGHT; y++) {
                if (y == Utility.reverseIndex(heights_level[x])) {
                    isShouldGenerateTree(x, y);
                }
            }
        }
    }

    public void renderWorld(Graphics g) {
        for (int x = 0; x < GAME_WIDTH; x++) {
            for (int y = 0; y < GAME_HEIGHT; y++) {
                blocks[x][y].draw(g);
            }
        }

        player.draw(g);
    }
}