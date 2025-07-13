package me.felek.game.screens;

import me.felek.game.Game;
import me.felek.game.LevelSO;
import me.felek.game.World;
import me.felek.game.lang.LangHandler;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class NewWorldScreen {
    public NewWorldScreen() {
        JFrame frame = new JFrame();;
        frame.setTitle(Game.FULL_NAME);
        frame.setSize(800, 500);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        DefaultBackgroundPanel panel = new DefaultBackgroundPanel();
        panel.setLayout(null);

        JButton back = new JButton(LangHandler.getTranslation("back_button"));
        back.setBounds(10, 5, 80, 70);
        back.addActionListener(e -> {
            new SingleplayerWorldSelection();
            frame.dispose();
        });

        JLabel levelNameLabel = new JLabel(LangHandler.getTranslation("create_new_world.level_name") + ": ");
        levelNameLabel.setBounds(110, 5, 155, 40);
        levelNameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 26));

        JTextField levelNameField = new JTextField(15);
        levelNameField.setBounds(270, 5, 150, 40);

        JButton createGameButton = getCreateNewGameButton(levelNameField, frame);

        panel.add(back);
        panel.add(levelNameLabel);
        panel.add(levelNameField);
        panel.add(createGameButton);

        frame.add(panel);

        frame.setVisible(true);
    }

    private static JButton getCreateNewGameButton(JTextField levelNameField, JFrame frame) {
        JButton createGameButton = new JButton("Create Game");
        createGameButton.setFont(new Font("Segoe UI", Font.PLAIN, 28));
        createGameButton.setBounds(535, 380, 245, 77);
        createGameButton.addActionListener(e -> {
            try {
                new File("worlds/" + levelNameField.getText() + "/").mkdir();
                new File("worlds/" + levelNameField.getText() + "/data/").mkdir();
                new File("worlds/" + levelNameField.getText() + "/info.json").createNewFile();

                try(BufferedWriter bw = new BufferedWriter(new FileWriter("worlds/" + levelNameField.getText() + "/info.json"))){
                    bw.write("{\n");
                    bw.write("  \"name\": \"" + levelNameField.getText() + "\",\n");
                    bw.write("  \"world_version\": \"" + World.WORLD_VERSION + "\"\n");
                    bw.write("}");
                }catch (IOException exc){
                    throw new RuntimeException(exc);
                }

                try(BufferedWriter bw = new BufferedWriter(new FileWriter("worlds/" + levelNameField.getText() + "/player.json"))){
                    bw.write("{\n");
                    bw.write("  \"posX\": 1,\n");
                    bw.write("  \"posY\": 1\n");
                    bw.write("}");
                }catch (IOException exc){
                    exc.printStackTrace();
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            Game.player.moveTo(1, 1);

            Game.worldName = levelNameField.getText();
            Game.world.generateBlocks();
            LevelSO.saveLevel("level0");
            new GameScreen();

            frame.dispose();
        });
        return createGameButton;
    }
}
