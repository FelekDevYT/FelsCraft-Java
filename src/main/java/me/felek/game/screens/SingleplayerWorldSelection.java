package me.felek.game.screens;

import me.felek.game.Game;
import me.felek.game.LevelSO;
import me.felek.game.World;
import me.felek.game.lang.LangHandler;
import me.felek.game.utils.JSONParser;
import me.felek.game.utils.VersionUtil;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class SingleplayerWorldSelection {
    public  SingleplayerWorldSelection() {
        JFrame frame = new JFrame();;
        frame.setTitle(Game.FULL_NAME);
        frame.setSize(800, 500);
        frame.setResizable(false);
        frame.setLayout(null);

        JComboBox<String> worlds = new JComboBox<>();
        worlds.setBounds(105, 10, 330, 65);
        for(String obj : Objects.requireNonNull(new File("worlds/").list())){
            worlds.addItem(obj);
        }

        JButton back = new JButton(LangHandler.getTranslation("single_player_selection.back_button"));
        back.setBounds(10, 5, 80, 70);
        back.addActionListener(e -> {
            new MainMenu();
            frame.dispose();
        });

        JButton openWorld = getOpenJButton(worlds, frame);

        JButton newWorld = new JButton(LangHandler.getTranslation("single_player_selection.new_world_button"));
        newWorld.setBounds(225, 375, 210, 80);

        frame.add(worlds);
        frame.add(openWorld);
        frame.add(newWorld);
        frame.add(back);

        frame.setVisible(true);
    }

    private static JButton getOpenJButton(JComboBox<String> worlds, JFrame frame) {
        JButton openWorld = new JButton(LangHandler.getTranslation("single_player_selection.open_world_button"));
        openWorld.setBounds(10, 375, 210, 80);
        openWorld.addActionListener((e) -> {
            Game.worldName = worlds.getSelectedItem().toString();
            try {
                Game.worldVersion = JSONParser.parseString(Files.readString(Path.of("worlds/" + Game.worldName + "/info.json")), "world_version");
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

            new GameScreen();
            frame.dispose();
        });
        return openWorld;
    }
}
