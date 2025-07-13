package me.felek.game.screens;

import me.felek.game.Game;
import me.felek.game.lang.LangHandler;
import me.felek.lib.logUtils.LogLevel;
import me.felek.lib.logUtils.Logger;

import javax.swing.*;

public class MainMenu{
    public MainMenu(){
        Logger.log(LogLevel.INFO, "Opening GUI.");
        JFrame frame = new JFrame();;
        frame.setTitle(Game.FULL_NAME);
        frame.setSize(800, 500);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DefaultBackgroundPanel panel = new DefaultBackgroundPanel();
        panel.setLayout(null);

        JButton singlePlayerButton = new JButton(LangHandler.getTranslation("main_menu.single_player_button"));
        singlePlayerButton.setBounds(250, 100, 250, 50);
        singlePlayerButton.addActionListener((e) -> {
            Logger.log(LogLevel.INFO, "Moving screen to single player world selection menu.");
            new SingleplayerWorldSelection();
            frame.dispose();
        });

        JButton settingsButton = new JButton(LangHandler.getTranslation("main_menu.settings_button"));
        settingsButton.setBounds(250, 170, 250, 50);
        settingsButton.addActionListener((e) -> {
            Logger.log(LogLevel.INFO, "Moving screen to settings menu.");
            new SettingScreen();
            frame.dispose();
        });

        JButton aboutButton = new JButton(LangHandler.getTranslation("main_menu.about_button"));
        aboutButton.setBounds(250, 230, 250, 50);

        JButton quitButton = new JButton(LangHandler.getTranslation("main_menu.quit_button"));
        quitButton.setBounds(250, 300, 250, 50);
        quitButton.addActionListener((e) -> {
            Logger.log(LogLevel.INFO, "Quitting...");
            System.exit(10);
        });

        panel.add(singlePlayerButton);
        panel.add(settingsButton);
        panel.add(aboutButton);
        panel.add(quitButton);
        Logger.log(LogLevel.OK, "GUI opened, menu has been started.");

        frame.add(panel);

        frame.setVisible(true);
    }

}
