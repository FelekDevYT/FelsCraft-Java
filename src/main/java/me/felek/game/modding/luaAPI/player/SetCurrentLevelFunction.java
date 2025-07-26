package me.felek.game.modding.luaAPI.player;

import me.felek.game.managers.LevelManager;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;

public class SetCurrentLevelFunction extends OneArgFunction {
    @Override
    public LuaValue call(LuaValue levelNumber) {
        LevelManager.levelPointer = levelNumber.checkint();

        return null;
    }
}
