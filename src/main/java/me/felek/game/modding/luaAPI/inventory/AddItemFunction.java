package me.felek.game.modding.luaAPI.inventory;

import me.felek.game.managers.BlockManager;
import me.felek.game.managers.InventoryManager;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;

public class AddItemFunction extends OneArgFunction {
    @Override
    public LuaValue call(LuaValue itemName) {
        InventoryManager.addItemToInventory(BlockManager.getBlockTypeAsName(itemName.checkjstring()));

        return null;
    }
}
