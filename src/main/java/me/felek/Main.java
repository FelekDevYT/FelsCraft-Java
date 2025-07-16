package me.felek;

import me.felek.game.Game;
import me.felek.game.lang.LangHandler;
import me.felek.game.lang.Language;
import me.felek.game.managers.ModManager;
import me.felek.game.screens.MainMenu;
import me.felek.lib.logUtils.LogLevel;
import me.felek.lib.logUtils.Logger;

import javax.swing.*;
import java.awt.*;

public class Main extends JPanel{
    public static final JFrame frame = new JFrame();

    public Main(){
        setBackground(Color.CYAN);
    }

    public static void main(String[] args) {
        Logger.log(LogLevel.INFO, "Setting up language.");
        LangHandler.loadLanguage(Language.en_US);
        Logger.log(LogLevel.OK, "Language loaded.");
        ModManager.initModManager();
        ModManager.loadMods();
        new MainMenu();
    }

    public void paint(Graphics g){
        super.paintComponents(g);

        Game.world.renderWorld(g);

        Game.overlay.draw(g);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 15));
        g.drawString("@by " + Game.AUTHOR, 1140,670);
        g.drawString(Game.FULL_NAME, 10, 15);
    }

    public void redraw(){
        repaint();
    }
}