package me.felek.game.modding.luaAPI.debugging;

import me.felek.lib.logUtils.LogLevel;
import me.felek.lib.logUtils.Logger;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.TwoArgFunction;

public class LogFunction extends TwoArgFunction {
    @Override
    public LuaValue call(LuaValue type, LuaValue msg) {
        Logger.log(LogLevel.valueOf(type.tojstring().toUpperCase()), msg.tojstring());

        return null;
    }
}
