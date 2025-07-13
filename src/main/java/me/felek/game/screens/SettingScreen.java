package me.felek.game.screens;

import me.felek.game.Game;
import me.felek.game.lang.LangHandler;
import me.felek.game.lang.Language;
import me.felek.game.utils.JSONParser;
import me.felek.lib.logUtils.LogLevel;
import me.felek.lib.logUtils.Logger;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class SettingScreen {
    public SettingScreen() {
        Logger.log(LogLevel.INFO, "Opening GUI.");
        JFrame frame = new JFrame();;
        frame.setTitle(Game.FULL_NAME);
        frame.setSize(800, 500);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DefaultBackgroundPanel panel = new DefaultBackgroundPanel();
        panel.setLayout(null);

        JLabel settingLabel = new JLabel(LangHandler.getTranslation( "settings.settings_label"));
        settingLabel.setFont(new Font("Segoe UI", Font.PLAIN, 48));
        settingLabel.setBounds(160, 0, 430, 71);
        settingLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel languageLabel = new JLabel(LangHandler.getTranslation("settings.language_label") + ": ");
        languageLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        languageLabel.setBounds(10, 70, 110, 41);

        JComboBox<String> languageComboBox = new JComboBox<>();
        for(String obj : Objects.requireNonNull(new File("settings/lang/").list())){
            try {
                languageComboBox.addItem(JSONParser.parseString(Files.readString(Path.of("settings/lang/" + obj)), "this.language"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        languageComboBox.setBounds(115, 70, 180, 40);

        JButton applyButton = getApplyButton(languageComboBox, frame);

        panel.add(settingLabel);
        panel.add(languageLabel);
        panel.add(languageComboBox);
        panel.add(applyButton);

        frame.add(panel);

        frame.setVisible(true);
        Logger.log(LogLevel.OK, "GUI opened, menu has been started.");
    }

    private static JButton getApplyButton(JComboBox<String> languageComboBox, JFrame frame) {
        JButton applyButton = new JButton(LangHandler.getTranslation("settings.apply_button"));
        applyButton.setFont(new Font("Segoe UI", Font.PLAIN, 36));
        applyButton.setBounds(265, 380, 230, 62);
        applyButton.addActionListener(e -> {
            String lang = Objects.requireNonNull(languageComboBox.getSelectedItem()).toString();
            for(String obj : Objects.requireNonNull(new File("settings/lang/").list())){
                try {
                    Path path = Path.of("settings/lang/" + obj);
                    if(JSONParser.parseString(Files.readString(path), "this.language").equals(lang)){
                        LangHandler.loadLanguage(Language.valueOf(JSONParser.parseString(Files.readString(path), "this.system_lang")));
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            Logger.log(LogLevel.INFO, "Applying language change.");
            Logger.log(LogLevel.INFO, "Moving screen to main menu.");
            new MainMenu();
            frame.dispose();
        });
        return applyButton;
    }
}
