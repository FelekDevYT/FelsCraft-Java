package me.felek.game.modding;

import me.felek.lib.logUtils.LogLevel;
import me.felek.lib.logUtils.Logger;
import org.luaj.vm2.LuaFunction;
import org.luaj.vm2.LuaValue;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class EventBus {
    private static final Map<String, List<LuaFunction>> listeners = new HashMap<>();

    public static void subscribe(String eventName, LuaFunction handler) {
        listeners.computeIfAbsent(eventName, list -> new LinkedList<>()).add(handler);
    }

    public static void unsubscribe(String eventName, LuaFunction handler) {
        if (listeners.containsKey(eventName)) {
            listeners.get(eventName).remove(handler);
            return;
        }

        Logger.log(LogLevel.ERROR, "Can not unsubscribe handler, because handler is not exists");
    }

    public static void call(String eventName, Object... args) {
        List<LuaFunction> handlers = listeners.get(eventName);

        if (handlers != null) {
            for (LuaFunction handler : handlers) {
                try {
                    LuaValue[] varargs = new LuaValue[args.length];

                    for (int i = 0; i < args.length; i++) {
                        varargs[i] = LuaValue.valueOf(args[i].toString());
                    }

                    handler.invoke(varargs);
                } catch (Exception exc) {
                    Logger.log(LogLevel.ERROR, "Failed to call event " + eventName);
                }
            }
        }
    }
}
