package me.felek.game.modding.luaAPI.scheduler;

import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;

public class StopSchedulerFunction extends OneArgFunction {
    @Override
    public LuaValue call(LuaValue schedulerName) {
        SchedulerAPI.stopScheduler(schedulerName.checkjstring());

        return null;
    }
}
