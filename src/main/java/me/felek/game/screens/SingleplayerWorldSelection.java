package me.felek.game.screens;

import me.felek.game.Game;
import me.felek.game.LevelSO;
import me.felek.game.Player;
import me.felek.game.World;
import me.felek.game.lang.LangHandler;
import me.felek.game.utils.JSONParser;
import me.felek.game.utils.VersionUtil;
import me.felek.lib.logUtils.LogLevel;
import me.felek.lib.logUtils.Logger;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class SingleplayerWorldSelection {
    public  SingleplayerWorldSelection() {
        Logger.log(LogLevel.INFO, "Opening GUI.");
        JFrame frame = new JFrame();;
        frame.setTitle(Game.FULL_NAME);
        frame.setSize(800, 500);
        frame.setResizable(false);

        DefaultBackgroundPanel panel = new DefaultBackgroundPanel();
        panel.setLayout(null);

        Logger.log(LogLevel.INFO, "Loading worlds to list.");
        JComboBox<String> worlds = new JComboBox<>();
        worlds.setBounds(105, 10, 330, 65);
        for(String obj : Objects.requireNonNull(new File("worlds/").list())){
            worlds.addItem(obj);
        }
        Logger.log(LogLevel.OK, "Levels are loaded.");

        JButton back = new JButton(LangHandler.getTranslation("back_button"));
        back.setBounds(10, 5, 80, 70);
        back.addActionListener(e -> {
            Logger.log(LogLevel.INFO, "Moving screen to main menu.");
            new MainMenu();
            frame.dispose();
        });

        JButton openWorld = getOpenJButton(worlds, frame);

        JButton newWorld = new JButton(LangHandler.getTranslation("single_player_selection.new_world_button"));
        newWorld.setBounds(225, 375, 210, 80);
        newWorld.addActionListener(e -> {
            Logger.log(LogLevel.INFO, "Moving screen to world creation screen.");
            new NewWorldScreen();
            frame.dispose();
        });

        panel.add(worlds);
        panel.add(openWorld);
        panel.add(newWorld);
        panel.add(back);

        frame.add(panel);

        frame.setVisible(true);
        Logger.log(LogLevel.OK, "GUI opened, menu has been started.");
    }

    private static JButton getOpenJButton(JComboBox<String> worlds, JFrame frame) {
        JButton openWorld = new JButton(LangHandler.getTranslation("single_player_selection.open_world_button"));
        openWorld.setBounds(10, 375, 210, 80);
        openWorld.addActionListener((e) -> {
            Game.worldName = worlds.getSelectedItem().toString();
            try {
                String json = Files.readString(Path.of("worlds/" + Game.worldName + "/info.json"));
                Game.worldVersion = JSONParser.parseString(json, "world_version");
                json = Files.readString(Path.of("worlds/" + Game.worldName + "/player.json"));
                Game.player.moveTo(JSONParser.parseInt(json, "posX"), JSONParser.parseInt(json, "posY"));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            if(!Game.worldVersion.equals(World.WORLD_VERSION)){
                switch (VersionUtil.compareVersion(Game.worldVersion)){
                    case 0 ->{// old
                        JOptionPane.showMessageDialog(frame, LangHandler.getTranslation("world_old_version"), Game.FULL_NAME, JOptionPane.ERROR_MESSAGE);
                    }
                    case 2 ->{//new
                        JOptionPane.showMessageDialog(frame, LangHandler.getTranslation("world_new_version"), Game.FULL_NAME, JOptionPane.ERROR_MESSAGE);
                    }
                    case -1 ->{//error while loading
                        JOptionPane.showMessageDialog(frame, LangHandler.getTranslation("world_old_version"), Game.FULL_NAME, JOptionPane.ERROR_MESSAGE);
                    }
                }
                JOptionPane.showMessageDialog(frame, LangHandler.getTranslation("world_load_error"), Game.FULL_NAME, JOptionPane.ERROR_MESSAGE);
                return;
            }

            Logger.log(LogLevel.INFO, "Loading world...");
            Logger.log(LogLevel.INFO, "Moving screen to world.");
            new GameScreen();
            frame.dispose();
        });
        return openWorld;
    }
}
