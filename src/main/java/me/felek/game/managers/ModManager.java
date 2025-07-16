package me.felek.game.managers;

import me.felek.game.modding.Mod;
import me.felek.game.modding.luaAPI.block.RegisterBlockFunction;
import me.felek.game.modding.luaAPI.debugging.LogFunction;
import me.felek.game.modding.luaAPI.inventory.AddItemFunction;
import me.felek.game.modding.luaAPI.inventory.SetItemInSlot;
import me.felek.game.modding.luaAPI.inventory.GetItemInSlot;
import me.felek.lib.logUtils.LogLevel;
import me.felek.lib.logUtils.Logger;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.lib.jse.JsePlatform;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ModManager {
    public static final Globals GLOBALS = JsePlatform.standardGlobals();

    public static final int MAX_LOADED_MODS = 4;

    public static List<Mod> loadedMods = new ArrayList<>();

    public static void initModManager() {
        LuaTable fc = new LuaTable();

        LuaTable debugging = new LuaTable();
        LuaTable inventory = new LuaTable();
        LuaTable block = new  LuaTable();

        block.set("register", new RegisterBlockFunction());

        inventory.set("addItem", new AddItemFunction());
        inventory.set("setItemInSlot", new SetItemInSlot());
        inventory.set("getItemInSlot", new GetItemInSlot());

        debugging.set("log", new LogFunction());

        fc.set("debugging", debugging);
        fc.set("inventory", inventory);
        fc.set("block", block);

        GLOBALS.set("fc", fc);
    }

    public static void loadMods() {
        for (String dirName : Objects.requireNonNull(new File("mods/").list())) {
            Logger.log(LogLevel.INFO, String.format("Loading %s", dirName));
            Mod mod = Mod.getMod(dirName);
            mod.loadMod();
            loadedMods.add(mod);
            Logger.log(LogLevel.INFO, String.format("Loaded %s", dirName));
        }
    }

    public static void setupAll() {
        for (Mod mod : loadedMods) {
            Logger.log(LogLevel.INFO, String.format("Calling setup()"));
            mod.callSetup();
            Logger.log(LogLevel.INFO, String.format("Setup called"));
        }
    }

    public static void updateAll() {
        for (Mod mod : loadedMods) {
            mod.callUpdate();
        }
    }
}
