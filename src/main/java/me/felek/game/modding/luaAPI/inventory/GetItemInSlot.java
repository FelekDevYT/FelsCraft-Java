package me.felek.game.modding.luaAPI.inventory;

import me.felek.game.managers.BlockManager;
import me.felek.game.managers.InventoryManager;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;

public class GetItemInSlot extends OneArgFunction {
    @Override
    public LuaValue call(LuaValue slotNumber) {
        return LuaValue.valueOf(BlockManager.getBlockNameAsBlockType(InventoryManager.getItemAtPos(slotNumber.toint())));
    }
}
