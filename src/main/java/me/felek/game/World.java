package me.felek.game;

import me.felek.lib.utils.MathUtils;

import java.awt.*;

import static me.felek.game.Game.*;

public class World {
    public static final String WORLD_VERSION = "0.4";

    public Block[][] blocks = new Block[GAME_WIDTH][GAME_HEIGHT];
    public int[] heights_level = new int[GAME_WIDTH];

    public Block getBlockAt(int x, int y) {
        return blocks[x][y];
    }

    public void setBlock(int x, int y, Block block) {
        blocks[x][y] = block;
    }

    public void generateBlocks(){
        for(int x = 0; x < GAME_WIDTH; x++){
            heights_level[x] = MathUtils.random(20, 25);
            if(x != 0){
                if(Math.abs(heights_level[x] - heights_level[x-1]) > 2){
                    while(Math.abs(heights_level[x] - heights_level[x-1]) > 2){
                        heights_level[x] = MathUtils.random(20, 20 + Math.abs(heights_level[x] - heights_level[x-1]));
                    }
                }
            }
        }

        for(int x = 0; x < GAME_WIDTH; x++){
            for(int y = 0; y < GAME_HEIGHT; y++){
                if(coalLevel.isShouldGenerate(Utility.reverseIndex(y))){
                    blocks[x][y] = new Block((BLOCK_SIZE * x), (BLOCK_SIZE * y), BlockType.COAL);
                }else if(ironLevel.isShouldGenerate(Utility.reverseIndex(y))){
                    blocks[x][y] = new Block((BLOCK_SIZE * x), (BLOCK_SIZE * y), BlockType.IRON);
                }else if(diamondLevel.isShouldGenerate(Utility.reverseIndex(y))){
                    blocks[x][y] = new Block((BLOCK_SIZE * x), (BLOCK_SIZE * y), BlockType.DIAMOND);
                }else if(y < Utility.reverseIndex(heights_level[x])){
                    blocks[x][y] = new Block((BLOCK_SIZE * x), (BLOCK_SIZE * y), BlockType.SKY);
                }else if(y < Utility.reverseIndex(heights_level[x] - 2)){
                    blocks[x][y] = new Block((BLOCK_SIZE * x), (BLOCK_SIZE * y), BlockType.GRASS);
                }else{
                    blocks[x][y] = new Block((BLOCK_SIZE * x), (BLOCK_SIZE * y), BlockType.STONE);
                }
            }
        }
    }

    public void renderWorld(Graphics g){
        for(int x = 0; x < GAME_WIDTH; x++){
            for(int y = 0; y < GAME_HEIGHT; y++){
                blocks[x][y].draw(g);
            }
        }

        player.draw(g);
    }
}
