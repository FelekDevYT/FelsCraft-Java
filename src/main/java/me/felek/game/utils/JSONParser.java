package me.felek.game.utils;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonValue;

import java.io.StringReader;
import java.util.List;

public class JSONParser {
    public static String parseString(String json, String key) {
        JsonObject obj = Json.createReader(new StringReader(json)).readObject();
        return obj.getString(key);
    }

    public static int parseInt(String json, String key) {
        JsonObject obj = Json.createReader(new StringReader(json)).readObject();
        return obj.getInt(key);
    }
}
