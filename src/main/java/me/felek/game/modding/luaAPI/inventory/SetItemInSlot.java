package me.felek.game.modding.luaAPI.inventory;

import me.felek.game.managers.BlockManager;
import me.felek.game.managers.InventoryManager;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.TwoArgFunction;

public class SetItemInSlot extends TwoArgFunction {
    @Override
    public LuaValue call(LuaValue slotNumber, LuaValue itemName) {
        InventoryManager.setItemInPos(slotNumber.toint(), BlockManager.getBlockTypeAsName(itemName.checkjstring()));

        return null;
    }
}
