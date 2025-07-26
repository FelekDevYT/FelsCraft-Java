package me.felek.game.modding.luaAPI.player;

import me.felek.game.Game;
import me.felek.game.managers.LevelManager;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.ZeroArgFunction;

public class GetCurrentLevelFunction extends ZeroArgFunction {
    @Override
    public LuaValue call() {
        return LuaValue.valueOf(LevelManager.levelPointer);
    }
}
