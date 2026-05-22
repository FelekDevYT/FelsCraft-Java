package me.felek.game.modding.luaAPI.block;

import me.felek.game.managers.BlockManager;
import me.felek.game.utils.drawUtils.Color;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.TwoArgFunction;

public class RegisterBlockFunction extends TwoArgFunction {
    @Override
    public LuaValue call(LuaValue name, LuaValue color) {
        LuaTable colorTable = color.checktable();

        Color c = new Color(colorTable.get("r").checkint(), colorTable.get("g").checkint(), colorTable.get("b").checkint());

        return LuaValue.valueOf(BlockManager.registerBlock(name.checkjstring(), c));
    }
}
