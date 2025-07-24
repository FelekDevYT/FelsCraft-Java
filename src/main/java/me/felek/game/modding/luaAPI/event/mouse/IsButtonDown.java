package me.felek.game.modding.luaAPI.event.mouse;

import me.felek.game.modding.luaAPI.event.EventVal;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;

import java.awt.event.MouseEvent;

public class IsButtonDown extends OneArgFunction {
    @Override
    public LuaValue call(LuaValue luaValue) {
        String button = luaValue.checkjstring().toLowerCase();

        return switch(button) {
            case "left" -> LuaValue.valueOf(EventVal.leftButtonDown);
            case "right" -> LuaValue.valueOf(EventVal.rightButtonDown);
            default -> LuaValue.FALSE;
        };
    }
}
