package me.felek.game.modding.luaAPI.scheduler;

import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.ThreeArgFunction;

public class RunSchedulerFunction extends ThreeArgFunction {
    @Override
    public LuaValue call(LuaValue schedulerName, LuaValue schedulerFunction, LuaValue delay) {
        SchedulerAPI.newScheduler(schedulerName.checkjstring(), delay.checkint(), schedulerFunction.checkfunction());

        return null;
    }
}
