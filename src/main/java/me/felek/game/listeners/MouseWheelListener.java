package me.felek.game.listeners;

import me.felek.game.managers.InventoryManager;

import java.awt.event.MouseWheelEvent;

public class MouseWheelListener implements java.awt.event.MouseWheelListener {
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        //MouseWheelEvent.WHEEL_BLOCK_SCROLL(1) - down
        //MouseWheelEvent.WHEEL_UNIT_SCROLL(-1) - up
        if(e.getWheelRotation() == -1) {
            InventoryManager.current_slot++;
        }else if(e.getWheelRotation() == 1) {
            InventoryManager.current_slot--;
        }

        fixRotation();
    }

    private void fixRotation() {
        InventoryManager.current_slot = Math.floorMod(InventoryManager.current_slot, 9);
    }
}
