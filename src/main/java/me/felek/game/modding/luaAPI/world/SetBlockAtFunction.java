package me.felek.game.modding.luaAPI.world;

import me.felek.game.Block;
import me.felek.game.Game;
import me.felek.game.managers.BlockManager;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.ThreeArgFunction;

public class SetBlockAtFunction extends ThreeArgFunction {
    @Override
    public LuaValue call(LuaValue xPos, LuaValue yPos, LuaValue type) {
        Game.world.setBlock(new Block(xPos.checkint(), yPos.checkint(), BlockManager.getBlockTypeAsName(type.checkjstring())));

        return null;
    }
}
