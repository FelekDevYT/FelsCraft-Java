package me.felek.game.lang;

import me.felek.game.utils.JSONParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class LangHandler {
    public static Language language;
    public static String langJSON;

    public static String getTranslation(String key){
        return JSONParser.parseString(langJSON, key);
    }

    public static void loadLanguage(Language language){
        LangHandler.language = language;
        try {
            langJSON = Files.readString(Path.of(String.format("settings/lang/%s.json", language.getLanguage())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
