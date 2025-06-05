package me.felek.game.screens;

import me.felek.game.Game;
import me.felek.game.lang.LangHandler;

import javax.swing.*;

public class MainMenu {
    public MainMenu(){
        JFrame frame = new JFrame();;
        frame.setTitle(Game.FULL_NAME);
        frame.setSize(800, 500);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JButton singlePlayerButton = new JButton(LangHandler.getTranslation("main_menu.single_player_button"));
        singlePlayerButton.setBounds(250, 100, 250, 50);
        singlePlayerButton.addActionListener((e) -> {
            new SingleplayerWorldSelection();
            frame.dispose();
        });

        JButton settingsButton = new JButton(LangHandler.getTranslation("main_menu.settings_button"));
        settingsButton.setBounds(250, 170, 250, 50);
        settingsButton.addActionListener((e) -> {
            new SettingScreen();
            frame.dispose();
        });

        JButton aboutButton = new JButton(LangHandler.getTranslation("main_menu.about_button"));
        aboutButton.setBounds(250, 230, 250, 50);

        JButton quitButton = new JButton(LangHandler.getTranslation("main_menu.quit_button"));
        quitButton.setBounds(250, 300, 250, 50);
        quitButton.addActionListener((e) -> {
            System.exit(10);
        });

        frame.add(singlePlayerButton);
        frame.add(settingsButton);
        frame.add(aboutButton);
        frame.add(quitButton);

        frame.setVisible(true);
    }
}
