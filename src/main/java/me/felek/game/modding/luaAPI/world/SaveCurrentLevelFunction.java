package me.felek.game.modding.luaAPI.world;

import me.felek.game.Game;
import me.felek.game.LevelSO;
import me.felek.game.managers.LevelManager;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.ZeroArgFunction;

public class SaveCurrentLevelFunction extends ZeroArgFunction {
    @Override
    public LuaValue call() {
        LevelSO.loadLevel("level" + LevelManager.levelPointer);

        return null;
    }
}
