package me.felek.game.modding.luaAPI.player;

import me.felek.game.Game;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.TwoArgFunction;

public class SetCordsFunction extends TwoArgFunction {
    @Override
    public LuaValue call(LuaValue xPos, LuaValue yPos) {
        Game.player.moveTo(xPos.checkint(), yPos.checkint());

        return null;
    }
}
