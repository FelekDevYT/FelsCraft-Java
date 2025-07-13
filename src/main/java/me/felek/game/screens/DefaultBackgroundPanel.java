package me.felek.game.screens;

import me.felek.game.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.MetalTheme;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DefaultBackgroundPanel extends JPanel {
    private final BufferedImage background;
    private final Color dominantColor;
    private final String selectedTheme;

    public DefaultBackgroundPanel() {
        BufferedImage tempBackground = null;
        Color tempColor = new Color(36, 189, 77);

        try {
            tempBackground = ImageIO.read(new File("assets/menubg.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        background = tempBackground;
        dominantColor = (background != null)
                ? calculateDominantColor(background)
                : tempColor;

        selectedTheme = selectThemeBasedOnColor(dominantColor);
        applySelectedTheme();
    }

    private String selectThemeBasedOnColor(Color color) {
        float[] hsb = Color.RGBtoHSB(
                color.getRed(),
                color.getGreen(),
                color.getBlue(),
                null
        );

        float hue = hsb[0];
        float saturation = hsb[1];
        float brightness = hsb[2];

        if (brightness < 0.2) return "Dark";
        if (saturation < 0.3) return "Light";
        if (hue < 0.1 || hue > 0.9) return "Warm";
        if (hue > 0.3 && hue < 0.6) return "Cool";

        return "Default";
    }

    private void applySelectedTheme() {
        try {
            switch (selectedTheme) {
                case "Dark":
                    UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarkLaf");
                    break;
                case "Light":
                    UIManager.setLookAndFeel("com.formdev.flatlaf.FlatLightLaf");
                    break;
                case "Warm":
                    setCustomWarmTheme();
                    break;
                case "Cool":
                    setCustomCoolTheme();
                    break;
                default:
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void setCustomWarmTheme() {
        MetalTheme warmTheme = new DefaultMetalTheme() {
            @Override
            public ColorUIResource getMenuBackground() {
                return new ColorUIResource(dominantColor.darker().darker());
            }

            @Override
            public ColorUIResource getMenuSelectedBackground() {
                return new ColorUIResource(dominantColor);
            }

            @Override
            public ColorUIResource getControl() {
                return new ColorUIResource(new Color(255, 230, 200));
            }

            @Override
            public ColorUIResource getControlTextColor() {
                return new ColorUIResource(Color.BLACK);
            }

            public ColorUIResource getMenuTextColor() {
                return new ColorUIResource(Color.WHITE);
            }
        };

        MetalLookAndFeel.setCurrentTheme(warmTheme);
        try {
            UIManager.setLookAndFeel(new MetalLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setCustomCoolTheme() {
        MetalTheme coolTheme = new DefaultMetalTheme() {
            @Override
            public ColorUIResource getMenuBackground() {
                return new ColorUIResource(dominantColor.darker().darker());
            }

            @Override
            public ColorUIResource getMenuSelectedBackground() {
                return new ColorUIResource(dominantColor.brighter());
            }

            @Override
            public ColorUIResource getControl() {
                return new ColorUIResource(new Color(200, 230, 255));
            }

            @Override
            public ColorUIResource getControlTextColor() {
                return new ColorUIResource(Color.BLACK);
            }

            public ColorUIResource getMenuTextColor() {
                return new ColorUIResource(Color.WHITE);
            }
        };

        MetalLookAndFeel.setCurrentTheme(coolTheme);
        try {
            UIManager.setLookAndFeel(new MetalLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Color calculateDominantColor(BufferedImage image) {
        Map<Integer, Integer> colorFrequency = new HashMap<>();
        int width = image.getWidth();
        int height = image.getHeight();

        int step = Math.max(1, width / 50);
        for (int y = 0; y < height; y += step) {
            for (int x = 0; x < width; x += step) {
                int rgb = image.getRGB(x, y);
                int alpha = (rgb >> 24) & 0xFF;

                if (alpha > 128) {
                    int groupedRGB = (rgb >> 8) & 0xFFFF;
                    colorFrequency.put(groupedRGB, colorFrequency.getOrDefault(groupedRGB, 0) + 1);
                }
            }
        }

        int maxCount = 0;
        int dominantGroup = 0;
        for (Map.Entry<Integer, Integer> entry : colorFrequency.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                dominantGroup = entry.getKey();
            }
        }

        int totalR = 0, totalG = 0, totalB = 0;
        int count = 0;
        for (int y = 0; y < height; y += step) {
            for (int x = 0; x < width; x += step) {
                int rgb = image.getRGB(x, y);
                if (((rgb >> 8) & 0xFFFF) == dominantGroup) {
                    totalR += (rgb >> 16) & 0xFF;
                    totalG += (rgb >> 8) & 0xFF;
                    totalB += rgb & 0xFF;
                    count++;
                }
            }
        }

        if (count > 0) {
            return new Color(
                    totalR / count,
                    totalG / count,
                    totalB / count
            );
        }
        return new Color(36, 189, 77);
    }

    @Override
    public Component add(Component comp) {
        if(!(comp instanceof JLabel)){
            comp.setBackground(dominantColor);
        }else {
            comp.setBackground(new Color(0, 0, 0, 0));
        }

        if (comp instanceof JComponent) {
            ((JComponent) comp).setOpaque(true);
        } else if (comp instanceof AbstractButton) {
            comp.setBackground(adjustColorForButton(dominantColor));
        }

        return super.add(comp);
    }

    private Color adjustColorForButton(Color base) {
        return new Color(
                Math.max(0, base.getRed() - 20),
                Math.max(0, base.getGreen() - 20),
                Math.max(0, base.getBlue() - 20)
        );
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (background != null) {
            for(int x = 0; x < Game.SCREEN_WIDTH; x += 54){
                for(int y = 0; y < Game.SCREEN_HEIGHT; y += 54){
                    g.drawImage(background, x, y, 54, 54, null);
                }
            }
        }
    }
}