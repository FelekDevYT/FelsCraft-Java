package me.felek;

import me.felek.game.Game;
import me.felek.game.lang.LangHandler;
import me.felek.game.lang.Language;
import me.felek.game.screens.MainMenu;
import me.felek.game.screens.SettingScreen;

import javax.swing.*;
import java.awt.*;

public class Main extends JPanel{
    public static final JFrame frame = new JFrame();

    public Main(){
        setBackground(Color.CYAN);
    }

    public static void main(String[] args) {
        LangHandler.loadLanguage(Language.en_US);
        new MainMenu();
        //new SettingScreen();
    }

    public void paint(Graphics g){
        super.paintComponents(g);
        Game.world.renderWorld(g);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 15));
        g.drawString("@by " + Game.AUTHOR, 1140,670);
        g.drawString(Game.FULL_NAME, 10, 15);
    }

    public void redraw(){
        repaint();
    }
}