package me.felek.game.modding;

import me.felek.game.managers.ModManager;
import me.felek.game.utils.IO;
import me.felek.game.utils.JSONParser;
import me.felek.lib.logUtils.LogLevel;
import me.felek.lib.logUtils.Logger;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;

import java.io.File;

public class Mod {
    private String name;
    private String version;
    private String author;

    private LuaValue chunk;

    private Mod(String name) {
        this.name = name;
    }

    public static Mod getMod(String name) {
        return new Mod(name);
    }

    public void loadMod() {
        String pathStr = "mods/" + name + "/";
        File path = new File(pathStr);

        if (!path.exists()) {
            Logger.log(LogLevel.ERROR, "Could not find mod " + name);
            return;
        }

        String cfgJSON = IO.readAllText(new File(pathStr + "config/config.json"));

        version = JSONParser.parseString(cfgJSON, "version");
        author = JSONParser.parseString(cfgJSON, "author");

        chunk = ModManager.GLOBALS.load(IO.readAllText(new File(pathStr + "/scripts/main.lua")));

        chunk.call();

        LuaValue setupFunc = ModManager.GLOBALS.get("setup");
        if (setupFunc.isnil()) {
            Logger.log(LogLevel.INFO, "Couldn't find setup function");
            return;
        }
    }

    public void callSetup() {
        ModManager.GLOBALS.get("setup").call();
    }

    public void callUpdate() {
        ModManager.GLOBALS.get("update").call();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
