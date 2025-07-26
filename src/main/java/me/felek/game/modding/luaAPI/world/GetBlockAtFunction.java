package me.felek.game.modding.luaAPI.world;

import me.felek.game.Block;
import me.felek.game.Game;
import me.felek.game.managers.BlockManager;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.TwoArgFunction;

public class GetBlockAtFunction extends TwoArgFunction {
    @Override
    public LuaValue call(LuaValue xPos, LuaValue yPos) {
        Block block = Game.world.getBlockAt(xPos.checkint(), yPos.checkint());

        return LuaValue.valueOf(BlockManager.getBlockNameAsBlockType(block.getType()));
    }
}
