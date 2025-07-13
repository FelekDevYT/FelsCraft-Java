package me.felek.game.listeners;

import me.felek.game.Game;
import me.felek.game.LevelSO;

import java.awt.*;
import java.awt.event.KeyEvent;

public class KeyListener implements java.awt.event.KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A -> {
                Game.player.move(-1, 0);
            }
            case KeyEvent.VK_D -> {
                Game.player.move(1, 0);
            }
            case KeyEvent.VK_S -> {
                Game.player.move(0, 1);
            }
            case KeyEvent.VK_W -> {
                Game.player.move(0, -1);
            }
            case KeyEvent.VK_F1 -> {
                LevelSO.saveLevel("level0");
            }//Removed F2 openLevel because I want
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
