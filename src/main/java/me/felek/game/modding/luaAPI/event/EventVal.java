package me.felek.game.modding.luaAPI.event;

import me.felek.game.modding.EventBus;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EventVal implements MouseListener, KeyListener {
    @Override
    public void mousePressed(MouseEvent e) {
        EventBus.call("mouse.pressed", e.getButton() == MouseEvent.BUTTON1 ? "left" : "right", e.getX(), e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        EventBus.call("mouse.released", e.getButton() == MouseEvent.BUTTON1 ? "left" : "right", e.getX(), e.getY());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        EventBus.call("mouse.clicked", e.getButton() == MouseEvent.BUTTON1 ? "left" : "right", e.getX(), e.getY());
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        EventBus.call("mouse.entered", e.getButton() == MouseEvent.BUTTON1 ? "left" : "right", e.getX(), e.getY());
    }

    @Override
    public void mouseExited(MouseEvent e) {}

    //====================================================
    //=================KEYBOARD LISTENERS=================
    //====================================================

    @Override
    public void keyTyped(KeyEvent e) {
        EventBus.call("keyboard.keyTyped", e.getKeyChar());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        EventBus.call("keyboard.keyPressed", e.getKeyChar());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        EventBus.call("keyboard.keyReleased", e.getKeyChar());
    }

    //====================================================
    //=================WORLD LISTENERS====================
    //====================================================

    public static void callBlockPlaced(String oldBlockType, String newBlockType, int posX, int posY) {
        EventBus.call("world.block_placed", oldBlockType, newBlockType, posX, posY);
    }

    public static void callBlockBroken(String blockType, int posX, int posY) {
        EventBus.call("world.block_broken", blockType, posX, posY);
    }

    public static void callPlayerMovedEvent(int oldX, int oldY, int dx, int dy) {
        EventBus.call("player.moved", oldX, oldY, dx, dy);
    }
}
