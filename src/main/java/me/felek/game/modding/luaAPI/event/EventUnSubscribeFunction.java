package me.felek.game.modding.luaAPI.event;

import me.felek.game.modding.EventBus;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.TwoArgFunction;

public class EventUnSubscribeFunction extends TwoArgFunction {
    @Override
    public LuaValue call(LuaValue eventName, LuaValue eventFunction) {
        EventBus.unsubscribe(eventName.checkjstring(), eventFunction.checkfunction());

        return null;
    }
}
