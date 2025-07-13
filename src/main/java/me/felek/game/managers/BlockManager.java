package me.felek.game.managers;

import me.felek.game.BlockType;
import me.felek.lib.logUtils.LogLevel;
import me.felek.lib.logUtils.Logger;

import java.awt.*;
import java.util.*;
import java.util.List;

public class BlockManager {
    public static Map<String, BlockType> blockTypes;
    
    static {
        blockTypes = new HashMap<String, BlockType>();
        blockTypes.put("grass", new BlockType(new Color(42, 147, 42)));
        blockTypes.put("stone", new BlockType(new Color(128, 128, 128)));
        blockTypes.put("sky", new BlockType(new Color(7, 139, 250)));
        blockTypes.put("iron", new BlockType(new Color(117, 111, 102)));
        blockTypes.put("coal", new BlockType(new Color(60, 60, 60)));
        blockTypes.put("diamond", new BlockType(new Color(11, 135, 150)));
        blockTypes.put("player", new BlockType(Color.ORANGE));
        blockTypes.put("leaves", new BlockType(new Color(33, 224, 33)));
        blockTypes.put("log", new BlockType(new Color(99, 61, 31)));
        blockTypes.put("dirt", new BlockType(new Color(59, 39, 23)));
    }

    public static String getBlockNameAsBlockType(BlockType blockType) {
        Set<String> entries =  blockTypes.keySet();
        for (String name : entries) {
            if (blockTypes.get(name).equals(blockType)) {
                return name;
            }
        }

        return "";
    }
    
    public static BlockType getBlockTypeAsName(String name) {
        name = name.toLowerCase();
        if (!blockTypes.containsKey(name)) {
            System.out.println("Unknown block type: " + name);
            return null;
        }

        return blockTypes.get(name);
    }

    public static void addBlock(String blockName, BlockType blockType) {
        blockName = blockName.toLowerCase();

        if (!blockTypes.containsKey(blockName)) {
            Logger.log(LogLevel.ERROR, "Failed to add new block " + blockName);
            return;
        }

        blockTypes.put(blockName, blockType);
    }
}