package me.felek.game.modding.luaAPI.event;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EventVal implements MouseListener {
    public static boolean leftButtonDown = false;
    public static boolean rightButtonDown = false;

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            leftButtonDown = true;
        }
        else if(e.getButton() == MouseEvent.BUTTON3) {
            rightButtonDown = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            leftButtonDown = false;
        }
        else if(e.getButton() == MouseEvent.BUTTON3) {
            rightButtonDown = false;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
