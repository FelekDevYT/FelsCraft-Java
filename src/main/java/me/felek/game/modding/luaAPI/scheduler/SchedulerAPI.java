package me.felek.game.modding.luaAPI.scheduler;

import org.luaj.vm2.LuaFunction;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class SchedulerAPI {
    private static final Map<String, Timer> schedulers = new HashMap<>();

    public static void newScheduler(String schedulerName, int delay, LuaFunction handler) {
        Timer timer = new Timer(delay, acl -> {
            handler.call();
        });//in milliseconds
        timer.start();

        schedulers.put(schedulerName, timer);
    }

    public static void stopScheduler(String schedulerName) {
        schedulers.get(schedulerName).stop();
        schedulers.remove(schedulerName);
    }
}
