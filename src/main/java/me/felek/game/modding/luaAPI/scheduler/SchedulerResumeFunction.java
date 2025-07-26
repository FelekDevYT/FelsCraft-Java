package me.felek.game.modding.luaAPI.scheduler;

import me.felek.game.modding.luaAPI.SchedulerAPI;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;

public class SchedulerResumeFunction extends OneArgFunction {
    @Override
    public LuaValue call(LuaValue luaValue) {
        SchedulerAPI.resumeScheduler(luaValue.checkjstring());

        return null;
    }
}
