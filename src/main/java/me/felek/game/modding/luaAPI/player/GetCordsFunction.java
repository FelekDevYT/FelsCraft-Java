package me.felek.game.modding.luaAPI.player;

import me.felek.game.Game;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.ZeroArgFunction;

public class GetCordsFunction extends ZeroArgFunction {
    @Override
    public LuaValue call() {
        LuaTable cords = new LuaTable();

        cords.set("x", Game.player.getX());
        cords.set("y", Game.player.getY());

        return cords;
    }
}
