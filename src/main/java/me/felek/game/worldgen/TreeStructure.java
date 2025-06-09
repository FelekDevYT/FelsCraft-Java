package me.felek.game.worldgen;

import me.felek.game.Block;
import me.felek.game.BlockType;
import me.felek.game.Game;

public class TreeStructure implements Structure {
    @Override
    public Grid getGrid() {
        Grid grid = new Grid(3, 4);

        grid.addBlock(1, 0, new Block(1, 0, BlockType.LEAVES));
        grid.addBlock(0, 1, new Block(0, 1, BlockType.LEAVES));
        grid.addBlock(1, 1, new Block(0, 1, BlockType.LEAVES));
        grid.addBlock(2, 1, new Block(0, 1, BlockType.LEAVES));

        grid.addBlock(1, 2, new Block(1, 2, BlockType.LOG));
        grid.addBlock(1, 3, new Block(1, 3, BlockType.LOG));

        return grid;
    }

    @Override
    public StructureRarity getRarity() {
        return new StructureRarity(5, 6, 5, 6);
    }
}
